import java.util.Properties

import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder}
import org.apache.kafka.streams.kstream.KStream

object KafkaStream extends App {

  def getKafkaTestConfig: Properties = {
    val props = new Properties
    props.put("bootstrap.servers", "localhost:9092")
    props.put("application.id", "consumer-myapp")
    props.put("retries","10")
    props.put("acks","1")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
    props.put("schema.registry.url", "http://127.0.0.1:8081")
    props.put("auto.offset.reset", "earliest")
    props
  }
  val builder = new StreamsBuilder
  val textLines: KStream[String, String] = builder.stream[String, String]("data-twitter2")
  val branches = textLines.branch(
    (key,value) => value.isEmpty(),
    (key,value) => true
  )

  branches(0).to("data-twitter3")
  branches(1).to("data-twitter4")
  val streams: KafkaStreams = new KafkaStreams(builder.build(), getKafkaTestConfig)


}
