package services

import models.State

class GameService {

  val lineOscilator = State(
    Set((-1,0) , (0, 0), (1, 0))
  )

  val glider = State(
    Set((-1,-1) , (0, -1), (1, -1), (1, 0), (0, 1))
  )

  var gameState = glider


  def setState(state: State): State = {
    gameState = state
    gameState
  }

  def playNext(): State = {
      gameState = gameState.allNextAlive
      gameState
  }

}

