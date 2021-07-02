package models

case class State(allCurrentlyAlive: Set[(Int, Int)]) {
  private val allAliveAsBlocks = allCurrentlyAlive.map{case (x, y) => Block(x, y)}

  private val allToConsider: Set[Block] = allAliveAsBlocks ++ allAliveAsBlocks.flatMap(_.tempNeighbours(allAliveAsBlocks))

  def allNextAlive: State =
    State(allToConsider.collect{case b if b.toBeAlive(allAliveAsBlocks) => (b.x, b.y)})
}

object State {

  private val regex = """(-?\d+)\|(-?\d+)""".r("x", "y")

  def apply(seq: Seq[String]): State = State(
    seq.flatMap { str =>
      regex
        .findFirstMatchIn(str)
        .map{ matched =>
          (matched.group("x").toInt, matched.group("y").toInt)
      }
    }.toSet
  )
}