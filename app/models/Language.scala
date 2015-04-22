package models

import play.api.db._
import play.api.Play.current
import anorm.SQL
import anorm.SqlQuery
import anorm.RowParser
import anorm._
import anorm.SqlParser._
import anorm.ResultSetParser
import anorm.SqlQueryResult

case class Language (lang_id: String, lang_desc: String)

object Language {
  val langPars: RowParser[Language] = {
	str("lang_id") ~
	str("lang_desc") map {
    	case lang_id ~ lang_desc => Language(lang_id,lang_desc)
    }
  }
  val langsPars: ResultSetParser[List[Language]] = {
	langPars *
  }

  def getCurrntLang(lang1:String): List[Language] = DB.withConnection {
	implicit connection => 
    val res: SqlQueryResult = SQL("""select * from language where lang_id = {lang1};""").on("lang1" -> lang1).executeQuery() 

    res.statementWarning match {
      case Some(warning) => List(Language("sqlcode",res.statementWarning.get.getSQLState()))
      case _ => { 
        val result1 = res.as(langPars *)
        if (result1.isEmpty == true)
          List(Language("sqlcode","100")) ++ result1 
        else
          List(Language("sqlcode","0")) ++ result1  
      }
    }
  }

  def getOtherLangs(lang1:String): List[Language] = DB.withConnection {
	implicit connection => 
      SQL("""select * from language where lang_id <> {lang1};""").on("lang1" -> lang1).as(langsPars) 
  }
} 
