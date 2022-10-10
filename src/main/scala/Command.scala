import Orientation.*

val gridWidth = 10
val gridHeight = 20

val minX = 0;
val maxX = gridWidth - 1;
val minY = 0;
val maxY = gridHeight - 1

enum Command:

  case MoveForward, RotateClockwise, RotateAntiClockwise

  def apply(state: RoverState): RoverState = (this ,state) match
    case (MoveForward,RoverState(pos@Position(x,y),orientation)) =>
      state.copy(position = orientation match
        case North => pos.copy(y = if y == minY then maxY else y - 1)
        case East => pos.copy(x = if x == maxX then minX else x + 1)
        case South => pos.copy(y = if y == maxY then minY else y + 1)
        case West => pos.copy(x = if x == minX then maxX else x - 1))
    case (RotateClockwise,RoverState(_,orientation)) =>
      state.copy(orientation = orientation.nextClockwise)
    case (RotateAntiClockwise,RoverState(_,orientation)) =>
      state.copy(orientation = orientation.nextAntiClockwise)

object Command:

  val all: List[Command] = Command.values.toList

  val none: List[Command] = List.empty[Command]