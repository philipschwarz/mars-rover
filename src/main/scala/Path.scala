case class Path(commands: List[Command], roverState: RoverState):

  /**
   * Grow this path one step further by getting the rover to
   * execute the given command, thereby entering a new state
   *
   * @param command the command to be executed
   * @return a new path grown from this one
   */
  def grow(command: Command) = Path(command::commands,command(roverState))

  override def toString: String = s"${commands.reverse.mkString(", ")} ===> $roverState"