scalaVersion := "2.12.8"

name := "movies-recommender"
organization := "eng.crissilva"
version := "1.0"

// https://mvnrepository.com/artifact/org.apache.mahout/mahout-mr
libraryDependencies += "org.apache.mahout" % "mahout-mr" % "0.12.2"

// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-client
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.7.2"
