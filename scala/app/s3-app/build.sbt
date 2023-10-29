ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "s3-app"
  )

fork := true
//due to the newer version of scala
//https://mvnrepository.com/artifact/org.apache.spark/spark-core
val sparkVersion = "3.4.1"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.11.534"
libraryDependencies += "org.apache.hadoop" % "hadoop-aws" % "3.2.2"

Compile / mainClass  := Some("Main")
assembly / mainClass := Some("Main")