package models

case class ViewModel(blocks: Set[(Int, Int)])

object ViewModel {
  val width = 60
  val height= 30
  val boxsize = 10
  def apply(state: State):ViewModel = ViewModel(state.allCurrentlyAlive)
}
