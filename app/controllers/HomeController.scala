package controllers

import models.{State, ViewModel}

import javax.inject._
import play.api._
import services.GameService
import views.html.index
import play.api.mvc._

@Singleton
class HomeController @Inject()(
                                val controllerComponents: ControllerComponents,
                                gameService: GameService,
                                view: index
                              ) extends BaseController {

  def index() = Action { r =>
    val cells: Seq[String] = r.queryString.getOrElse("cells", Seq.empty).flatMap(_.split(","))
    val tick: Boolean = r.queryString.get("tick").exists(_.contains("true"))


    val state = if (tick) {
      gameService.playNext()
    } else {
      gameService.setState(State(cells))
    }

    Ok(view(ViewModel(state)))
  }

}
