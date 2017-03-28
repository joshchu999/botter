name := "play"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.8"

val akkaVersion = "2.4.16"

lazy val play = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  cache,
  ws,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
  "com.typesafe.play" %% "play-slick" % "2.0.2",
  "im.actor" % "akka-scalapb-serialization_2.11" % "0.1.14",
  "mysql" % "mysql-connector-java" % "5.1.34"
)


dockerRepository := Some("joshchu00.no-ip.info:25000")
dockerBaseImage := "openjdk:8u121-jre"
dockerExposedPorts in Docker := Seq(9000, 9443)
