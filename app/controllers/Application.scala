package controllers

import play.api._
import play.api.mvc._
import models.Student
import play.api.Play.current

object Application extends Controller {

  def index = Action {
    val students = Student.getStudentsWithParser
    Ok(views.html.index(students))
  }

  def blog = Action {
    val students = Student.getStudentsWithParser
    Ok(views.html.blog(students))
  }
}
