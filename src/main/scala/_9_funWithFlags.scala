import akka.stream.scaladsl.Source
import scala.language.postfixOps
import scala.concurrent.duration._

object _9_funWithFlags extends AkkaApp {

  val numbers = Source(List(3, 4, 5, 3))

  val boom = Source.single("BOOM!!!")

  val ticks = Source.repeat("tick...").take(4)

//  ticks.concat(boom)
//    .throttle(1, 1 second)
//    .runForeach(println)

  Source
    .tick(0 seconds, 1 second, "Tick...")
    .take(4)
    .concat(Source.single("BOOM!"))
    .runForeach(println)
}
