package models

import org.scalatestplus.play.PlaySpec

class BlockSpec extends PlaySpec {

  "tempNeighbors" must {
    "have correct neighbours" in {
      val sut = Block(0, 0)
      val expected = Set((-1,-1),(-1,0),(-1,1),(0,1),(0,-1),(1,1),(1,0),(1,-1)).map{case (x, y) => Block(x, y, false)}

      sut.tempNeighbours(Set(sut)) mustBe expected
    }
  }

  "isNeighbor" must {
    "return true" when {
      val sut = Block(0, 0)
      Seq(
        ("to Left", (-1, 0)),
        ("to left and up", (-1, 1)),
        ("to left and down", (-1, -1)),
        ("above", (0, 1)),
        ("below", (0, -1)),
        ("to right", (1, 0)),
        ("to right and up", (1, 1)),
        ("to right and down", (1, -1))
      ).foreach{ case (where, (x, y)) =>
       s"neighbor is $where" in {
         val neighbor = Block(x,y)
         assert(sut.isNeighbour(neighbor))
       }
      }
    }
    "return false" when {
      "not a Neighbor" in {
        val sut = Block(0, 0)
        sut.isNeighbour(Block(1,2)) mustBe false
      }
    }
  }

  "numberOfNeighbours" should {
    "return 0" when {
      "there is no other blocks" in {
        val sut = Block(0, 0)
        val all = Set(sut)

        sut.numberOfNeighbours(all) mustBe 0
      }
      "there is no neighbors" in {
        val sut = Block(0, 0)
        val notANeighbour = Block(2, 2)
        val all = Set(sut, notANeighbour)

        sut.numberOfNeighbours(all) mustBe 0
      }
    }
    "return 1" when {
      "there is only 1 and its a neighbor"in {
        val sut = Block(0, 0)
        val neighbour = Block(1, 0)
        val all = Set(sut, neighbour)

        sut.numberOfNeighbours(all) mustBe 1
      }

      "there is many but only 1 is a neighbor" in {
        val sut = Block(0, 0)
        val neighbour = Block(1, 0)
        val notANeighbour = Block(2, 2)
        val all = Set(sut, neighbour, notANeighbour)

        sut.numberOfNeighbours(all) mustBe 1
      }
    }
  }

  "toBeAlive" must {
    "return true" when {
      "a block has 2 neighbors" in {
        val sut = Block(0,0)
        val all = Set((1, 1), (-1, -1)).map{case (x, y) => Block(x, y)}

        sut.toBeAlive(all) mustBe true
      }
      "a block has 3 neighbors" in {
        val sut = Block(0,0)
        val all = Set((1, 1), (-1, -1), (0, -1)).map{case (x, y) => Block(x, y)}

        sut.toBeAlive(all) mustBe true
      }
    }
    "return false" when {
      "a block has 1 neighbors" in {
        val sut = Block(0,0)
        val all = Set((1, 1)).map{case (x, y) => Block(x, y)}

        sut.toBeAlive(all) mustBe false
      }
      "a block has more than 3 neighbors" in {
        val sut = Block(0,0)
        val all = Set((1, 1), (-1, -1), (0, -1), (-1, 0)).map{case (x, y) => Block(x, y)}

        sut.toBeAlive(all) mustBe false
      }
    }
  }

}
