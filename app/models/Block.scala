package models

import models.Block.neighborGrid

case class Block(x: Int, y: Int, alive: Boolean = true) {

  def tempNeighbours(all: Set[Block]): Set[Block] = neighborGrid.map{case (dx, dy) => Block(x+dx, y+dy, all.exists(b => b.x == x+dx && b.y == y+dy))}

  def isNeighbour(other: Block): Boolean = {
    val dxdy = (x - other.x, y - other.y)
    neighborGrid.contains(dxdy)
  }

  def numberOfNeighbours(all: Set[Block]): Int = {
    val others: Set[Block] = all - this
    others.count(this.isNeighbour)
  }

  def toBeAlive(all: Set[Block]): Boolean = {
    numberOfNeighbours(all) match {
      case 2 if alive => true
      case 3 => true
      case _ => false
    }
  }
}

object Block {
  private val neighborGrid = {for {
    x <- Seq(-1, 0, 1)
    y <- Seq(-1, 0, 1)
  } yield (x, y)}.toSet  - ((0, 0))
}
