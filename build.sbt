ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.0"

lazy val root = (project in file("."))
  .settings(
    name := "mars-rover"
  )

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "5.0.0"
)