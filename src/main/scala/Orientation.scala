enum Orientation:

  case North, East, South, West

  def nextClockwise =
    if this == West then North
    else Orientation.fromOrdinal(ordinal + 1)

  def nextAntiClockwise =
    if this == North then West
    else Orientation.fromOrdinal(ordinal - 1)