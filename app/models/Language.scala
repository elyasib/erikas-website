package models

import play.api.db._
import play.api.Play.current
import anorm.SQL
import anorm.SqlQuery
import anorm.RowParser
import anorm._
import anorm.SqlParser._
import anorm.ResultSetParser


case class Language (lang_id: String, lang_desc: String)

object Language {
    val sqlGetLangs: SqlQuery = SQL("""select * from language where lang_id = {lang1} union select * from language where lang_id <> {lang1};""")
	val langPars: RowParser[Language] = {
		str("lang_id") ~
		str("lang_desc") map {
			case lang_id ~ lang_desc => Language(lang_id,lang_desc)
		}
	}
	val langsPars: ResultSetParser[List[Language]] = {
		langPars *
	}
	def getLangs(lang1:String): List[Language] = DB.withConnection {
		implicit connection => 
          sqlGetLangs.on("lang1" -> lang1).as(langsPars)
	}
} 
