package controllers

import play.api._
import play.api.mvc._
import models.Language
import play.api.Play.current

object Application extends Controller {

  def index = Action {
    val languages = Language.getLangs
    Ok(views.html.index(languages))
  }

  def blog = Action {
    val languages = Language.getLangs
    Ok(views.html.blog(languages))
  }
}
