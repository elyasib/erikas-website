package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.Play.current

object Application extends Controller {

  def home(langVar: String) = Action {
    val currntLang = Language.getCurrntLang(langVar)
    if (currntLang._1 == "0") {
      val otherLangs = Language.getOtherLangs(langVar)
        val langsNum:Int = otherLangs._2.length
        Ok(views.html.home(currntLang._2,otherLangs._2,langsNum))
    }
    else
      NotFound
  }

  def blog = Action {
    val currntLang = Language.getCurrntLang("en")
    Ok(views.html.blog(currntLang._2))
  }
}
