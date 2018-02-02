import com.github.play2war.plugin._

name := """Travel"""

version := "1.0"

Play2WarPlugin.play2WarSettings

Play2WarKeys.servletVersion := "3.0"

PlayKeys.externalizeResources := false

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  ehcache,
  jcache1,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.8.Final", // replace by your jpa implementation
  "mysql" % "mysql-connector-java" % "5.1.39",
  "com.typesafe.play" % "play-jdbc_2.10" % "2.4.0-RC1",
  "com.typesafe.play" %% "anorm" % "2.5.1"
)

//lazy val root = (project in file(".")).enablePlugins(PlayJava)

///scalaVersion := "2.11.6"

//crossScalaVersions := Seq("2.11.12", "2.12.4")

//libraryDependencies += guice

// Test Database
//libraryDependencies += "com.h2database" % "h2" % "1.4.196"

// Testing libraries for dealing with CompletionStage...
//libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
//libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test

// Make verbose tests
//testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))


