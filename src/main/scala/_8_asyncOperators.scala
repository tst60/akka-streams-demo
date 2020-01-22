import akka.stream.scaladsl._

import scala.concurrent.Future

object _8_asyncOperators extends AkkaApp {

  def process(n: Int) = Future {
    Thread.sleep(1000)
    println(s"Processing $n")
    n * n
  }

  Source(1 to 10)
    .map(_ * 2)
    .map(_ * 2)



  val result =
    Source(1 to 10)
      .mapAsyncUnordered(10)(process)
      .runWith(Sink.seq)

  result.foreach(println)
}
