package models

import play.api.db._
import play.api.Play.current
import anorm.SQL
import anorm.SqlQuery
import anorm.RowParser
import anorm._
import anorm.SqlParser._
import anorm.ResultSetParser


case class Student (id: Int, name: String)

object Student {
	val sql: SqlQuery = SQL("select * from table1 order by id desc")
	val studentParser: RowParser[Student] = {
		int("id") ~
		str("name") map {
			case id ~ name => Student(id,name)
		}
	}
	val studentsParser: ResultSetParser[List[Student]] = {
		studentParser *
	}
	def getStudentsWithParser: List[Student] = DB.withConnection {
		implicit connection => 
		sql.as(studentsParser)
	}
} 
