// Your sbt build file. Guides on how to write one can be found at
// http://www.scala-sbt.org/0.13/docs/index.html

//import ReleaseTransformations._
//resolvers += "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven/"
//val sparkVer = sys.props.getOrElse("spark.version", "2.4.0")
//val sparkBranch = sparkVer.substring(0, 3)
val defaultScalaVer = "2.12.8"
val scalaVer = sys.props.getOrElse("scala.version", defaultScalaVer)


scalaVersion := scalaVer

name := "graphframes"

organization := "org.graphframes"

version := (version in ThisBuild).value + s"-spark-gf"

isSnapshot := true

// All Spark Packages need a license
libraryDependencies ++= Seq("org.apache.spark"           %% "spark-core"           % "2.4.0",
  "org.apache.spark"           %% "spark-graphx"           % "2.4.0",
  "org.apache.spark"           %% "spark-sql"           % "2.4.0"
)

// Add Spark components this package depends on, e.g, "mllib", ....
//sparkComponents ++= Seq("graphx", "sql", "mllib")

// uncomment and change the value below to change the directory where your zip artifact will be created
// spDistDirectory := target.value

// add any Spark Package dependencies using spDependencies.
// e.g. spDependencies += "databricks/spark-avro:0.1"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.16"

//libraryDependencies += "org.scalatest" %% "scalatest" % defaultScalaTestVer % "test"

libraryDependencies += "com.github.zafarkhaja" % "java-semver" % "0.9.0" % "test" // MIT license

parallelExecution := false


// This fixes a class loader problem with scala.Tuple2 class, scala-2.11, Spark 2.x
fork in Test := true

// This and the next line fix a problem with forked run: https://github.com/scalatest/scalatest/issues/770
javaOptions in Test ++= Seq("-Xmx2048m", "-XX:ReservedCodeCacheSize=384m", "-XX:MaxPermSize=384m")
