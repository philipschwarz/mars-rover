
import Command.*
import Orientation.*
import org.specs2.*
import org.specs2.matcher.DataTables

class RoverCommandsSpec extends Specification with DataTables { def is =
  s2"""Rover should execute commands correctly $test"""
  def test =
     "x"  | "y" | "orientation"   | "command"            | "newX" | "newY" | "newOrientation"|>
      5   !  7  !  North          !  MoveForward         !  5     !  6     !  North          |
      5   !  7  !  East           !  MoveForward         !  6     !  7     !  East           |
      5   !  7  !  South          !  MoveForward         !  5     !  8     !  South          |
      5   !  7  !  West           !  MoveForward         !  4     !  7     !  West           |
      // moving near grid edges
      5   !  0  !  North          !  MoveForward         !  5     !  19    !  North          |
      9   !  7  !  East           !  MoveForward         !  0     !  7     !  East           |
      5   !  19 !  South          !  MoveForward         !  5     !  0     !  South          |
      0   !  7  !  West           !  MoveForward         !  9     !  7     !  West           |
      // rotate clockwise
      5   !  7  !  North          !  RotateClockwise     !  5     !  7     !  East           |
      5   !  7  !  East           !  RotateClockwise     !  5     !  7     !  South          |
      5   !  7  !  South          !  RotateClockwise     !  5     !  7     !  West           |
      5   !  7  !  West           !  RotateClockwise     !  5     !  7     !  North          |
      // rotate anti-clockwise
      5   ! 7   !  North          !  RotateAntiClockwise !  5     ! 7      !  West           |
      5   ! 7   !  East           !  RotateAntiClockwise !  5     ! 7      !  North          |
      5   ! 7   !  South          !  RotateAntiClockwise !  5     ! 7      !  East           |
      5   ! 7   !  West           !  RotateAntiClockwise !  5     ! 7      !  South          | {

      (x, y, orientation, command, newX, newY, newOrientation) =>

        val newPosition = Position(newX,newY)
        val roverState = RoverState(Position(x,y),orientation)

        val newRoverState = command(roverState)

        (newRoverState.position must beEqualTo(newPosition)).and(
         newRoverState.orientation must beEqualTo(newOrientation))
      }
    }