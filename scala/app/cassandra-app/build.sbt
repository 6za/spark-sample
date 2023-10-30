
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "cassandra-app"
  )

fork := true
//due to the newer version of scala
//https://mvnrepository.com/artifact/org.apache.spark/spark-core
val sparkVersion = "3.4.1"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"

libraryDependencies += "com.datastax.spark" % "spark-cassandra-connector_2.12" % "3.4.1"


Compile / mainClass  := Some("Main")
assembly / mainClass := Some("Main")