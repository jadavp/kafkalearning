import java.util.{Collections, Properties}

import com.example.Customer
import io.confluent.kafka.serializers.KafkaAvroDeserializer

import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

object KafkaAvroConsumer extends App {

  def getKafkaTestConfig: Properties = {
    val props = new Properties
    props.put("bootstrap.servers", "localhost:9092")
    props.put("group.id", "customer-consumer-myapp")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer")
    props.put("schema.registry.url", "http://127.0.0.1:8081")
    props.put("enable.auto.commit", "false")
    props.put("auto.offset.reset", "earliest")
    props.put("specific.avro.reader", "true")
    props
  }

  val customerConsumer = new KafkaConsumer[String,Customer](getKafkaTestConfig)
  while(true)
    {
      customerConsumer.subscribe(Collections.singleton("data-info"))
      val customerRecords:ConsumerRecords[String,Customer] = customerConsumer.poll(500)
      customerRecords.forEach( x =>println(x.value().getAge))
      customerConsumer.commitSync()
    }
}
