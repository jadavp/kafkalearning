import java.util.Properties

import com.example.Customer
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

object KafkaAvroProducer extends App {

  def getKafkaTestConfig: Properties = {
    val props = new Properties
    props.put("bootstrap.servers", "localhost:9092")
    props.put("group.id", "console-consumer-myapp")
    props.put("retries","10")
    props.put("acks","1")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
    props.put("schema.registry.url", "http://127.0.0.1:8081")
    props
  }

  val customer = Customer.newBuilder().setAge(12).setAutomatedEmail(true).setFirstName("Joker").setHeight(145).
    setLastName("idiot").setMiddleName("laggard").setWeight(123).build()

  val customerproducer = new KafkaProducer[String, Customer](getKafkaTestConfig)
  val customerrecord = new ProducerRecord[String, Customer]("data-info", customer)
  customerproducer.send(customerrecord, new Callback {
    override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit =
      try println(metadata)
    catch{
      case exception:Exception => exception.printStackTrace()
    }
  })
  customerproducer.flush()
  customerproducer.close()

}
