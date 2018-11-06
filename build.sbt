com.typesafe.sbt.SbtScalariform.scalariformSettings

sbtrelease.ReleasePlugin.releaseSettings

name := "pocAkka"

organization := "fr.drysoft"

scalaVersion := "2.12.0"

scalacOptions ++= Seq("-deprecation", "-feature", "-language:implicitConversions", "-Yrangepos", "-language:postfixOps")

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

val akkaStreamVersion = "2.5.17"
val akkaHttpVersion = "10.1.5"

libraryDependencies ++= Seq(
  "com.tzavellas" % "sse-guice" % "0.7.1"
  , "com.typesafe.akka" %% "akka-stream" % akkaStreamVersion
  , "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  , "ch.qos.logback" % "logback-classic" % "1.0.13"
  , "org.mockito" % "mockito-core" % "1.10.19" % Test
  , "org.scalatest" %% "scalatest" % "3.0.5" % Test
  , "com.typesafe.akka" %% "akka-stream-testkit" % akkaStreamVersion % Test
  , "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")

lazy val root = (project in file("."))

mainClass in (Compile, run) := Some("fr.drysoft.pocAkka.http.WebServer")