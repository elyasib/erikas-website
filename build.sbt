name := """erikasc"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  filters,
  jdbc,
  anorm,
  cache,
  ws,
  "org.webjars" % "bootstrap" % "3.3.1",
  "org.webjars" % "jquery" % "2.1.3",
  "org.webjars" % "jquery-ui" % "1.11.4",
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc41",
  "com.mohiva" %% "play-html-compressor" % "0.3.1"
)

LessKeys.cleancss in Assets := true

LessKeys.compress in Assets := true

//excludeFilter in (Assets, LessKeys.less) := "*.less"

includeFilter in (Assets, LessKeys.less) := "main.less"

