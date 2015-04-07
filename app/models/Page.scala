package models

import play.api.db._
import play.api.Play.current
import anorm.SQL
import anorm.SqlQuery
import anorm.RowParser
import anorm._
import anorm.SqlParser._
import anorm.ResultSetParser


case class Page (ptype_id: Int, ptype_desc: String)

object Page {
	val sql: SqlQuery = SQL("select * from page_type where ptype_id = 1;")
	val pagePars: RowParser[Page] = {
		int("ptype_id") ~
		str("ptype_desc") map {
			case ptype_id ~ ptype_desc => Page(ptype_id,ptype_desc)
		}
	}
	val pagesPars: ResultSetParser[List[Page]] = {
		pagePars *
	}
	def getPages: List[Page] = DB.withConnection {
		implicit connection => 
		sql.as(pagesPars)
	}
} 
