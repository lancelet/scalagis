import sbt._
import Keys._

object BuildSettings {
  val buildOrganization = "com.github.scalagis"
  val buildScalaVersion = "2.9.2"
  val buildVersion      = "0.1-SNAPSHOT"
  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := buildOrganization,
    scalaVersion := buildScalaVersion,
    version      := buildVersion
  )
}

object Resolvers {
  //val scalaToolsSnapshots = "Scala-Tools Snapshots" at
  //  "http://scala-tools.org/repo-snapshots"
  val sonatype = "Sonatype" at "https://oss.sonatype.org/content/groups/public"
  val allResolvers = Seq(sonatype)
}

object Dependencies {
  val jCommon     = "org.jfree" % "jcommon" % "1.0.17"
  val jFreeChart  = "org.jfree" % "jfreechart" % "1.0.14"
  val xmlGraphics = "org.apache.xmlgraphics" % "xmlgraphics-commons" % "1.4"
  val iText       = "com.lowagie" % "itext" % "2.1.5" intransitive()
  val scalaARM    = "com.jsuereth" %% "scala-arm" % "1.2"
  val allDependencies = Seq(
    jCommon, jFreeChart, xmlGraphics, iText, scalaARM
  )
}

object ScalaGisBuild extends Build {
  import Resolvers._
  import Dependencies._
  import BuildSettings._
  
  lazy val scalagis = Project("scalagis", file("."),
    settings = buildSettings ++ Seq(
      libraryDependencies := allDependencies,
      resolvers := allResolvers
    )) dependsOn(scalaSignal)
  
  val scalaSignalUri = uri("git://github.com/lancelet/scalasignal.git")
  lazy val scalaSignal = RootProject(scalaSignalUri)
}
