import com.example.Customer
import scala.io._
import java.io._

object SpecificRecordExample extends App{

  val customerBuilder3 =  Customer.newBuilder
  customerBuilder3.setAge(23)
  customerBuilder3.setFirstName("hello")
  customerBuilder3.setLastName("raju")
  customerBuilder3.setAutomatedEmail(true)
  customerBuilder3.setMiddleName("too")
  customerBuilder3.setHeight(123)
  customerBuilder3.setWeight(123)

  val customer = customerBuilder3.build()
  println(customer)


}
