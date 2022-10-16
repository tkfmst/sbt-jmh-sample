import Dependencies._

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "jmh-example",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "eu.timepit" %% "refined" % "0.10.1",
      "org.typelevel" %% "cats-core" % "2.8.0"
      ),
  )
  enablePlugins(JmhPlugin)
