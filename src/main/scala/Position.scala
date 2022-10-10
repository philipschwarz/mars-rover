case class Position(x: Int, y: Int):

  def distanceFrom(other: Position): Double =
    val horizontalDistance = math.abs(this.x - other.x)
    val verticalDistance = math.abs(this.y - other.y)
    math.sqrt(math.pow(horizontalDistance,2) + math.pow(verticalDistance,2))