name := "kafkalearning"

version := "1.0"

scalaVersion := "2.13.1"

lazy val akkaVersion = "2.6.5"

libraryDependencies ++= Seq(
 "org.apache.avro" % "avro" % "1.9.2",
 "com.sksamuel.avro4s" %% "avro4s-core" % "3.1.1",
 "org.apache.kafka" % "kafka-clients" % "2.5.0",
 "io.confluent" % "kafka-avro-serializer" % "5.5.0",
 "org.apache.kafka" % "kafka-streams" % "5.5.0-ce"
)

resolvers += "kafka-avro-serializer" at "https://packages.confluent.io/maven/"


avroSource := baseDirectory.value / "src/main/resources/"
avroStringType := "String"
avroEnableDecimalLogicalType := true
avroFieldVisibility := "private"
avroUseNamespace := false
