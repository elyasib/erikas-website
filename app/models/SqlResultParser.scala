package models

import play.api.db._
import play.api.Play.current
import play.api._
import anorm._
import anorm.{ResultSetParser,SqlQueryResult,RowParser,SQL,SqlQuery}, SqlParser._

object SqlResultParser {
  def parseSqlResult[T](result: SqlQueryResult,parser:RowParser[T],result0:T):(String,List[T]) = DB.withConnection {
    implicit connection =>
    result.statementWarning match {
      case Some(warning) => {
        (result.statementWarning.get.getSQLState(),List(result0))
      }
      case _ => { 
        val result1 = result.as(parser *)
        if (result1.isEmpty == true)
         ("100",List(result0))
        else
         ("0",result1)
      }
    }
  }
}

