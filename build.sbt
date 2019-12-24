organization in ThisBuild := "com.codingkapoor"
version in ThisBuild := "1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.12.8"

lagomCassandraEnabled in ThisBuild := false
lagomKafkaEnabled in ThisBuild := false

val pac4jVersion = "3.6.1"
val lagomPac4j = "org.pac4j" %% "lagom-pac4j" % "2.0.0"
val pac4jHttp = "org.pac4j" % "pac4j-http" % pac4jVersion
val pac4jJwt = "org.pac4j" % "pac4j-jwt" % pac4jVersion
val nimbusJoseJwt = "com.nimbusds" % "nimbus-jose-jwt" % "6.0"
val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test
val courier = "com.github.daddykotex" %% "courier" % "2.0.0"

lazy val `lagom-email-otp-auth-demo` = (project in file("."))
  .aggregate(`employee-api`, `employee-impl`, `passwordless-api`, `passwordless-impl`)

lazy val `employee-api` = (project in file("employee-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `employee-impl` = (project in file("employee-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      pac4jHttp,
      pac4jJwt,
      lagomPac4j,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`employee-api`)

lazy val `passwordless-api` = (project in file("passwordless-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `passwordless-impl` = (project in file("passwordless-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      nimbusJoseJwt,
      lagomScaladslTestKit,
      macwire,
      scalaTest,
      courier
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`passwordless-api`, `employee-api`)
