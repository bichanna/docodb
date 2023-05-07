ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "DocoDB",
    idePackagePrefix := Some("com.bichanna.docodb")
  )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "io.circe" %% "circe-parser" % "0.14.5" % Optional
)