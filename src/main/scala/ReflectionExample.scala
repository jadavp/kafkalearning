import com.sksamuel.avro4s.AvroSchema
import com.sksamuel.avro4s._

object ReflectionExample extends App {

  @AvroName("Wibble")
  @AvroNamespace("com.other")
  @AvroDoc("hello, is it me you're looking for?")
  val schema = AvroSchema[UserName]
  println(schema.toString)
}
