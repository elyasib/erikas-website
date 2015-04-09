package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.Play.current

object Application extends Controller {

  def home(langVar: String) = Action {
    val languages = Language.getLangs(langVar)
    Ok(views.html.home(languages))
  }

  def blog = Action {
    val languages = Language.getLangs("en")
    Ok(views.html.blog(languages))
  }
}
