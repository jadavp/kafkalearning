import java.io._
import org.apache.avro.Schema
import org.apache.avro.file.{DataFileReader, DataFileWriter}
import org.apache.avro.generic.{GenericDatumReader, GenericDatumWriter, GenericRecord, GenericRecordBuilder}
import org.apache.avro.io.DatumWriter

object GenericRecordExample extends App {

  val parser = new Schema.Parser
  val schema = parser.parse("{\n  \"type\" : \"record\",\n  \"namespace\": \"com.example\",\n  \"name\": \"CustomerAddress\",\n  \"fields\": [\n    {\"name\": \"first_name\", \"type\": \"string\", \"doc\":  \"This is first name of customer\"},\n    {\"name\": \"middle_name\", \"type\": [\"null\",\"string\"],\"default\" :  null},\n    {\"name\": \"last_name\", \"type\": \"string\"},\n    {\"name\": \"age\", \"type\": \"int\"},\n    {\"name\": \"height\", \"type\": \"int\"},\n    {\"name\": \"weight\", \"type\": \"int\"},\n    {\"name\": \"automated_email\", \"type\": \"boolean\", \"default\" :  true}\n  ]\n}")
  val cutomerBuilder = new GenericRecordBuilder(schema)
  cutomerBuilder.set("first_name", "prakash")
  cutomerBuilder.set("middle_name", "jayantialal")
  cutomerBuilder.set("last_name", "prakash")
  cutomerBuilder.set("age", 23)
  cutomerBuilder.set("height", 180)
  cutomerBuilder.set("weight", 90)
  cutomerBuilder.set("automated_email", true)
 val customer = cutomerBuilder.build()
  println(customer)


  val cutomerBuilder2 = new GenericRecordBuilder(schema)
  cutomerBuilder2.set("first_name", "prakash")
  cutomerBuilder2.set("middle_name", "jayantialal")
  cutomerBuilder2.set("last_name", "prakash")
  cutomerBuilder2.set("age", 23)
  cutomerBuilder2.set("height", 180)
  cutomerBuilder2.set("weight", 90)
  val customerwithDefault = cutomerBuilder2.build()
  println(customerwithDefault)


 /* val writer = new GenericDatumWriter[GenericRecord](schema)
  try {
    val dataFileWriter = new DataFileWriter[GenericRecord](writer)
    dataFileWriter.create(customer.getSchema(),new File("cutomer-generic.avro"))
    dataFileWriter.append(customer)
    println("File is written")
  }
  catch{
    case e:Exception => e.printStackTrace()
  }*/
  val reader = new GenericDatumReader[GenericRecord](schema)
  try {
    val dataFileReader = new DataFileReader[GenericRecord](new File("cutomer-generic.avro"), reader)
    var user: GenericRecord = null;
    while (dataFileReader.hasNext) {
      user = dataFileReader.next(user)
      println("Read user from Avro file: " + user)
    }
  }
  catch{
    case e:Exception => e.printStackTrace()
  }
}

