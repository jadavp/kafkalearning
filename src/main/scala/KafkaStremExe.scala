import java.util.Properties

import org.apache.kafka.common.serialization.{Serde, Serdes}
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig, Topology}

object KafkaStremExe extends App{



  def kafkaProps:Properties ={
    val props:Properties = new Properties
      props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092")
    props.put(StreamsConfig.APPLICATION_ID_CONFIG,"Streams")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,Serdes.String().getClass)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass)
    props
  }



  def createTopology:Topology ={
    val builder = new StreamsBuilder
    val inputStream:KStream[String,String] = builder.stream[String,String]("twitter-raw.data")
    inputStream.flatMap{ x =>
      println(x)
      "prakash"+ x
    }.to("data_value")

    builder.build
  }

  val stream: KafkaStreams = new KafkaStreams(createTopology,kafkaProps)
  stream.start()
}
