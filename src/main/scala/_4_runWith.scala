import akka.stream.scaladsl.{Keep, RunnableGraph, Sink, Source}

import scala.concurrent.Future

object _4_runWith extends AkkaApp {
  val result: Future[Seq[String]] =
    Source(List("Donatello", "Michaelangelo", "Leonardo", "Raphael"))
      .filter(_.endsWith("lo"))
      .map(_.toUpperCase())
      .runWith(Sink.seq)

  result.foreach(println)
}
