
@main def main: Unit =

  val sampleScenarios =

    List((Position(x = 5, y = 5), Orientation.North, Position(x = 3, y = 3)),
         (Position(x = 2, y = 2), Orientation.West,  Position(x = 4, y = 2)),
         (Position(x = 0, y = 0), Orientation.East,  Position(x = 4, y = 4)))

  for (origin, orientation, destination) <- sampleScenarios do

    val initialState = RoverState(origin, orientation)
    val desiredNumberOfPaths = 5

    println(s"Top $desiredNumberOfPaths paths to get from $origin with orientation $orientation to $destination: ")

    findPathsToDestination(initialState, destination)
      .take(desiredNumberOfPaths)
      .foreach(path => println(s"${path.commands.size} commands: $initialState ===> $path"))