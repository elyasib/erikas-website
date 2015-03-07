package models

import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._


case class Product (
	ean: Long, name: String, description: String)

object Product {
	var products = Set (
		Product (5010255079763L, "Paperclips", "Large plain pack of 1000"),
		Product (5018296344666L, "Giant Paperclips", "Giant Large plain pack of 1000")
	)
	def findAll = products.toList.sortBy(_.ean)
	def findByEan(ean: Long) = products.find(_.ean == ean)
}
