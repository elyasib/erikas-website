package models

import play.api.db._
import play.api.Play.current
import anorm._
import anorm.{ResultSetParser,SqlQueryResult,RowParser,SQL,SqlQuery}, SqlParser._
import models.SqlResultParser._

case class FScreen (page_id: Int, lang_id:String, fscrn_title:String, fscrn_abstract: String, fscrn_bgcolor: String,fscrn_bgimg_id: Option[Int]) {
  def this() = this(0,"","","","",Option(0))
}

object FScreen {
  val fScreenPars: RowParser[FScreen] = {
	int("page_id") ~
	str("lang_id") ~
	str("fscrn_title") ~
	str("fscrn_abstract") ~
	str("fscrn_bgcolor") ~
	get[Option[Int]]("fscrn_bgimg_id") map {
		case page_id ~ lang_id ~ fscrn_title ~ fscrn_abstract ~ fscrn_bgcolor ~ fscrn_bgimg_id => FScreen(page_id,lang_id,fscrn_title,fscrn_abstract,fscrn_bgcolor,fscrn_bgimg_id)
	}
  }
  def getFScreen(page_id:Int,lang_id:String): (String,List[FScreen]) = DB.withConnection {
	implicit connection => 
    val res: SqlQueryResult = SQL("""select * from front_screen where page_id = {page_id} and lang_id = {lang_id};""").on("page_id" -> page_id,"lang_id" -> lang_id).executeQuery() 
    val result0 = new FScreen()
    parseSqlResult[FScreen](res,fScreenPars,result0)
  }
} 


