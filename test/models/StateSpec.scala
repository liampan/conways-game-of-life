package models

import org.scalatestplus.play.PlaySpec

class StateSpec extends PlaySpec {

  "allNextAlive" must {
    "return the correct next state" when {
      "empty state" in {
        val sut = State(Set.empty)

        sut.allNextAlive mustBe sut
      }

      "one block that will die" in {
        val sut = State(Set(Block(0, 0)))

        sut.allNextAlive mustBe State(Set.empty)
      }

      "static state that never changes (square of 4, 2x2)" in {
        val all: Set[Block] = Set((0,0) , (0, 1), (1, 0), (1, 1)).map{case (x, y) => Block(x, y)}
        val sut = State(all)

        sut.allNextAlive mustBe sut
      }

      "oscillator (3 in a line)" in {
        val all: Set[Block] = Set((-1,0) , (0, 0), (1, 0)).map{case (x, y) => Block(x, y)}
        val allNext: Set[Block] = Set((0,1) , (0, 0), (0, -1)).map{case (x, y) => Block(x, y)}
        val sut = State(all)

        sut.allNextAlive mustBe State(allNext)
      }

    }
  }
}
