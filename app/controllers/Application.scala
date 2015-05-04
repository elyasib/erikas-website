package controllers

import play.api._
import play.api.mvc._
import models._
import models.Page._
import play.api.Play.current

object Application extends Controller {

  def home(langVar: String, pageName: String) = Action {
    var currPageName: String = ""
    if (pageName == "") 
      currPageName = "home"
    else
      currPageName = pageName

    val currntLang = Language.getCurrntLang(langVar)

    if (currntLang._1 == "0") {

      val otherLangs = Language.getOtherLangs(langVar)
      val langsNum:Int = otherLangs._2.length
      val page = getPage(currPageName)

      if (page._1 == "0") {

        val frontScreen = FScreen.getFScreen(page._2(0).page_id,langVar)
        val topMenu = TopMenu.getTopMenu(page._2(0).page_id,langVar) 
        Ok(views.html.home(currntLang._2,otherLangs._2,langsNum,frontScreen._2(0).fscrn_title,frontScreen._2(0).fscrn_abstract,topMenu._2))
      }
      else {
      NotFound
      }

    }
    else
      NotFound
  }

  def blog = Action {
    val currntLang = Language.getCurrntLang("en")
    Ok(views.html.blog(currntLang._2))
  }
}
