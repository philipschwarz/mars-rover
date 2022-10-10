import Command.*
import Orientation.*

/**
 * Take the paths the rover has already explored and add to them all the new paths
 * that can be grown from them by executing the available commands, avoiding paths
 * containing cycles.
 *
 * @param paths the paths the rover has explored so far
 * @param pastStates the states the rover has already entered
 * @param destination the position the rover needs to reach
 * @return the new paths obtained by growing the given ones
 */
def growPaths(paths: List[Path], pastStates: Set[RoverState], destination: Position): LazyList[List[Path]] =
  // Grow each of the paths already explored by the rover into one new path for each
  // possible command, except those causing the rover to enter a preexisting state
  // or to move away from the destination.
  val newPaths: List[Path] =
    for
      path <- paths
      command <- Command.all
      newPath = path.grow(command)
      distance = path.roverState.position distanceFrom destination
      newDistance = newPath.roverState.position distanceFrom destination
      if ! (pastStates contains newPath.roverState) && newDistance <= distance
    yield newPath
  if newPaths.isEmpty then LazyList(paths)
  else paths #:: growPaths(newPaths, pastStates ++ newPaths.map(_.roverState), destination)

/**
 * Find the paths the rover can take to reach the desired destination
 * @param initialState the rover's current position and orientation
 * @param destination the position the rover needs to reach
 * @return paths to desired destination
 */
def findPathsToDestination(initialState: RoverState, destination: Position): LazyList[Path] =
  val initialPath = Path(Command.none, initialState)
  for
    paths <- growPaths(paths = List(initialPath), pastStates = Set(initialState), destination)
    path <- paths
    if path.roverState.position == destination
  yield path