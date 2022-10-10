import Orientation.*
import org.specs2.mutable.Specification
import org.specs2.specification.Tables

import scala.language.postfixOps

class PositionSpec extends Specification:

  "Position" should {
    "know distance from other position" in position_should_know_distance_from_other_position
    "know distance from itself" in position_should_know_distance_from_itself
    "know distance from position in same row" in position_should_know_distance_from_position_in_same_row
    "know distance from position in same column" in position_should_know_distance_from_position_in_same_column
  }

  private def position_should_know_distance_from_other_position =
    Position(10,10) distanceFrom Position(13,14) must beEqualTo(5.0)

  private def position_should_know_distance_from_itself =
    Position(10, 10) distanceFrom Position(10, 10) must beEqualTo(0.0)

  private def position_should_know_distance_from_position_in_same_row =
    Position(10, 10) distanceFrom Position(15, 10) must beEqualTo(5.0)

  private def position_should_know_distance_from_position_in_same_column =
    Position(10, 10) distanceFrom Position(20, 10) must beEqualTo(10.0)

end PositionSpec