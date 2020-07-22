import java.util.Properties

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.kstream.{KStream, KTable}
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}

object KafkaStream extends App {
  type transEither = Either[String, String]

  def getKafkaTestConfig: Properties = {
    val props = new Properties
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "consumer-myapp")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass)
    props.put("schema.registry.url", "http://127.0.0.1:8081")
    props.put("auto.offset.reset", "earliest")
    props
  }

  val builder = new StreamsBuilder
  /*val textLines: KStream[String, String] = builder.stream[String, String]("twitter-raw.data")
  val transformSterm: KStream[String, String] = textLines.mapValues { v =>
    print(v)
    v.toString
  }*/

  val aggregateTable: KTable[String, String] = builder.table("twitter-raw.data")
  val table2 = aggregateTable.filter((k, v) => v.contains("modi"))
  table2.mapValues(v => "hello"+v).toStream().to("data-twitter4")

  sys.addShutdownHook {
    new Thread(() => streams.close())
  }
  /*transformSterm.to("data-twitter3")*/


  val streams: KafkaStreams = new KafkaStreams(builder.build(), getKafkaTestConfig)

  streams.start()


}
