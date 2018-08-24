import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "pty-sample",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.jetbrains.intellij.deps" % "pty4j" % "0.7.5" exclude("null", "purejavacomm"),
//    libraryDependencies += "com.github.purejavacomm" % "purejavacomm" % "1.0.2.RELEASE",
    unmanagedBase := baseDirectory.value / "lib"
  )
resolvers += Resolver.bintrayRepo("jetbrains", "intellij-third-party-dependencies") 