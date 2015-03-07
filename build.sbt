name := """erikasc"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.webjars" % "bootstrap" % "3.3.1",
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc41"
)
