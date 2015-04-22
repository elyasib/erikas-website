package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.Play.current

object Application extends Controller {

  def home(langVar: String) = Action {
    val currntLang = Language.getCurrntLang(langVar)
    var langVar1: String = ""
    var langVar2: String = ""
    if (currntLang(0).lang_desc == "0") {
      langVar1 = currntLang(0).lang_desc
      langVar2 = currntLang(1).lang_desc
    }
    else {
      langVar2 = "err"
      langVar1 = currntLang(0).lang_desc
      //langVar2 = currntLang(1).lang_desc
    }
    //val otherLangs = Language.getOtherLangs(langVar)
    Ok(views.html.home(currntLang,langVar1,langVar2))
  }

  def blog = Action {
    //val languages = Language.getLangs("en")
    val currntLang = Language.getCurrntLang("en")
    Ok(views.html.blog(currntLang))
  }
}
