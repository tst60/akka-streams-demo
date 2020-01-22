import akka.stream.scaladsl.{Keep, RunnableGraph, Sink, Source}

import scala.concurrent.Future

object _3_materializedValue extends AkkaApp {
  val stream: RunnableGraph[Future[Seq[Int]]] =
    Source(1 to 10)
      .map(_ * 2)
      .toMat(Sink.seq)(Keep.right)

  val result: Future[Seq[Int]] = stream.run()

  result.foreach(println)
}
