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

val akkaVersion = "2.5.17"

libraryDependencies ++= Seq(
  "com.tzavellas" % "sse-guice" % "0.7.1"
  , "com.typesafe.akka" %% "akka-stream" % akkaVersion
  , "org.mockito" % "mockito-core" % "1.10.19" % "test"
  , "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  , "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")

lazy val root = (project in file("."))