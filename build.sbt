import com.typesafe.sbt.web.SbtWeb
import play.PlayJava
import sbt._

name := """erikasc"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

scalacOptions += "-feature"

lazy val root = (project in file(".")).enablePlugins(PlayScala,SbtWeb)

libraryDependencies ++= Seq(
  filters,
  jdbc,
  anorm,
  cache,
  ws,
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "bootstrap" % "3.3.1",
  "org.webjars" % "jquery" % "2.1.3" intransitive(),
  "org.webjars" % "jquery-ui" % "1.11.4",
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc41",
  "com.mohiva" %% "play-html-compressor" % "0.3.1"
)

pipelineStages in Assets := Seq(concat,uglify,filter)

Concat.groups := Seq(
  "jsgrup.js" -> group(Seq("lib/jquery/jquery.min.js", "lib/jquery-ui/jquery-ui.min.js", "lib/bootstrap/js/bootstrap.min.js", "javascripts/main.js"))
)

Concat.parentDir := "javascripts/"

LessKeys.cleancss in Assets := true

LessKeys.compress in Assets := true

UglifyKeys.compress in Assets := true

UglifyKeys.mangle in Assets := true

UglifyKeys.sourceMap in Assets := false

includeFilter in uglify := GlobFilter("jsgrup.js")

includeFilter in (Assets,LessKeys.less) := "main.less"

includeFilter in filter :=  "*.map"

//excludeFilter in filter := "*.min.js"

