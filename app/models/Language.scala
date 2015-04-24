package models

import play.api.db._
import play.api.Play.current
import anorm._
import anorm.{ResultSetParser,SqlQueryResult,RowParser,SQL,SqlQuery}, SqlParser._
import models.SqlResultParser._

case class Language (lang_id: String, lang_desc: String) {
  def this() = this("","")
}

object Language {
  val langPars: RowParser[Language] = {
	str("lang_id") ~
	str("lang_desc") map {
    	case lang_id ~ lang_desc => Language(lang_id,lang_desc)
    }
  }

  def getCurrntLang(lang1:String): (String,List[Language]) = DB.withConnection {
	implicit connection => 
    val res: SqlQueryResult = SQL("""select * from language where lang_id = {lang1};""").on("lang1" -> lang1).executeQuery() 
    val result0 = new Language()
    parseSqlResult[Language](res,langPars,result0)
  }

  def getOtherLangs(lang1:String): (String,List[Language]) = DB.withConnection {
	implicit connection => 
    val res: SqlQueryResult = SQL("""select * from language where lang_id <> {lang1};""").on("lang1" -> lang1).executeQuery() 
    val result0 = new Language()
    parseSqlResult[Language](res,langPars,result0)
  }
} 
