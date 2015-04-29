package models

import play.api.db._
import play.api.Play.current
import anorm._
import anorm.{ResultSetParser,SqlQueryResult,RowParser,SQL,SqlQuery}, SqlParser._
import models.SqlResultParser._

case class Page (page_id: Int, page_name:String, ptype_id:Int) {
  def this() = this(0,"",0)
}

object Page {
  val pagePars: RowParser[Page] = {
	int("page_id") ~
	str("page_name") ~
	int("ptype_id") map {
		case page_id ~ page_name ~ ptype_id => Page(page_id,page_name,ptype_id)
	}
  }
  def getPage(page_name:String): (String,List[Page]) = DB.withConnection {
	implicit connection => 
    val res: SqlQueryResult = SQL("""select * from page where page_name = {page_name};""").on("page_name" -> page_name).executeQuery() 
    val result0 = new Page()
    parseSqlResult[Page](res,pagePars,result0)
  }
} 

case class PageType (ptype_id: Int, ptype_desc: String) {
  def this() = this(0,"")
}
object PageType {
  val pageTypePars: RowParser[PageType] = {
	int("ptype_id") ~
	str("ptype_desc") map {
		case ptype_id ~ ptype_desc => PageType(ptype_id,ptype_desc)
	}
  }
  def getPageType(ptype_id: Int): (String,List[PageType]) = DB.withConnection {
	implicit connection => 
    val res: SqlQueryResult = SQL("""select * from page_type where ptype_id = {ptype_id};""").on("etype_id" -> ptype_id).executeQuery() 
    val result0 = new PageType()
    parseSqlResult[PageType](res,pageTypePars,result0)
  }
} 

