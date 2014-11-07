name := "hmm"

version := "1.0"

scalaVersion := "2.11.3"

compileOrder := CompileOrder.JavaThenScala

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-language:implicitConversions"
)

libraryDependencies += "org.json4s" %% "json4s-native" % "3.2.11"
