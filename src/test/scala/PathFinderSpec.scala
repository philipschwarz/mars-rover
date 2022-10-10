
import Command.*
import Orientation.*
import org.specs2.*
import org.specs2.matcher.DataTables

// F = forward (move forward); L = left (rotate anticlockwise); R = right (rotate clockwise)
val FFLFF = List(MoveForward, MoveForward, RotateAntiClockwise, MoveForward, MoveForward)
val RRFF = List(RotateClockwise, RotateClockwise, MoveForward, MoveForward)
val RRF = List(RotateClockwise, RotateClockwise, MoveForward)
val LFFFLF = List(RotateAntiClockwise, MoveForward, MoveForward, MoveForward, RotateAntiClockwise, MoveForward)
val FFFFRFFFF = List(MoveForward, MoveForward, MoveForward, MoveForward, RotateClockwise, MoveForward, MoveForward, MoveForward, MoveForward)

class PathFinderSpec extends Specification with DataTables { def is =
  s2"""PathFinder should find the shortest path $test"""
  def test =
     "startX"  | "startY" | "orientation"   | "targetX" | "targetY" | "path"      |>
         5     !     5    !    North        !     3     !     3     !  FFLFF      |
         2     !     2    !    West         !     4     !     2     !  RRFF       |
         3     !    19    !    North        !     3     !     0     !  RRF        |
         2     !     4    !    South        !     5     !     3     !  LFFFLF     |
         0     !     0    !    East         !     4     !     4     !  FFFFRFFFF  | {

      (startX, startY, orientation, targetX, targetY, path) =>

        val startPosition = Position(startX,startY)
        val targetPosition = Position(targetX, targetY)
        val rover = RoverState(startPosition,orientation)

        val shortestPath =
          findPathsToDestination(rover, targetPosition)
            .head.commands.reverse

        shortestPath must beEqualTo(path)
      }
    }