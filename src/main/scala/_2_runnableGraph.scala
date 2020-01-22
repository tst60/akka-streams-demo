import akka.NotUsed
import akka.stream.scaladsl.{RunnableGraph, Sink, Source}

object _2_runnableGraph extends AkkaApp {

  val stream: RunnableGraph[NotUsed] =
    Source(1 to 10)
    .map(_ * 2)
    .to(Sink.foreach(println))

  stream.run()
}
