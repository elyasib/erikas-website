package models

import play.api.db._
import play.api.Play.current
import anorm._
import anorm.{ResultSetParser,SqlQueryResult,RowParser,SQL,SqlQuery}, SqlParser._
import models.SqlResultParser._

case class TopMenuItem (tmitem_id:Int, tmitem_name:String, tmitem_link:String) {
  def this() = this(0,"","")
}

object TopMenuItem {
  val TMenuItemPars: RowParser[TopMenuItem] = {
	int("tmitem_id") ~
	str("tmitem_name") ~
	str("tmitem_link") map {
		case tmitem_id ~ tmitem_name ~ tmitem_link => TopMenuItem(tmitem_id,tmitem_name,tmitem_link)
	}
  }
}

case class TopMenuOrder (page_id:Int, lang_id:String, tmenu_order:Int, tmitem_id:Int) {
  def this() = this(0,"",0,0)
}

object TopMenuOrder {
  val TMenuOrdPars: RowParser[TopMenuOrder] = {
	int("page_id") ~
	str("lang_id") ~
	int("tmenu_order") ~
	int("tmitem_id") map {
		case page_id ~ lang_id ~ tmenu_order ~ tmitem_id => TopMenuOrder(page_id,lang_id,tmenu_order,tmitem_id)
	}
  }
}

object TopMenu {
  def getTopMenu(page_id:Int,lang_id:String): (String,List[TopMenuItem]) = DB.withConnection {
    implicit connection => 
    val res: SqlQueryResult = SQL("""select b.* from (select * from top_menu where page_id = {page_id} and lang_id = {lang_id}) a join top_menu_item b on a.tmitem_id = b.tmitem_id order by tmenu_order;""").on("page_id" -> page_id,"lang_id" -> lang_id).executeQuery() 
    val result0 = new TopMenuItem()
    parseSqlResult[TopMenuItem](res,TopMenuItem.TMenuItemPars,result0)
  }
} 

