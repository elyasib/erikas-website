package models

import play.api.db._
import play.api.Play.current
import anorm.SQL
import anorm.SqlQuery
import anorm.RowParser
import anorm._
import anorm.SqlParser._
import anorm.SqlParser
import anorm.ResultSetParser

case class Page (page_id: Int, page_name:String, ptype_id:Int)

object Page {
  val sqlPage: SqlQuery = SQL("""select * from page where page_name = {page_name};""")
  val pagePars: RowParser[Page] = {
	int("page_id") ~
	str("page_name") ~
	int("ptype_id") map {
		case page_id ~ page_name ~ ptype_id => Page(page_id,page_name,ptype_id)
	}
  }
  def getPage(page_name:String): List[Page] = DB.withConnection {
	implicit connection => 
	sqlPage.on("page_name" -> page_name).as(pagePars *)
  }
} 

case class PageType (ptype_id: Int, ptype_desc: String)

object PageType {
  val sqlPageType: SqlQuery = SQL("""select * from page_type where ptype_id = {ptype_id};""")
  val pageTypePars: RowParser[PageType] = {
	int("ptype_id") ~
	str("ptype_desc") map {
		case ptype_id ~ ptype_desc => PageType(ptype_id,ptype_desc)
	}
  }
  def getPageType(ptype_id_ : Int): List[PageType] = DB.withConnection {
	implicit connection => 
	sqlPageType.on("ptype_id" -> ptype_id_).as(pageTypePars *)
  }
} 

