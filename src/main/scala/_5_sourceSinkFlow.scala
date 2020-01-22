import akka.NotUsed
import akka.stream.scaladsl.{Flow, Sink, Source}

object _5_sourceSinkFlow extends AkkaApp {

  val countingUp: Source[Int, NotUsed] = Source(1 to 100)

  val printIt = Sink.foreach(println)

  val squarer = Flow[Int].map(i => i * i)
  val doubler = Flow[Int].map(_ * 2)
  val evenNumbers = Flow[Int].filter(_ % 2 == 0)

  countingUp
      .map(_ * 2)
    .via(evenNumbers)
    .via(squarer)
    .runWith(printIt)

  countingUp
    .via(doubler)
    .runWith(printIt)
}
