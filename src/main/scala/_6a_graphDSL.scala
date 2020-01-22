import akka.stream.scaladsl.{Sink, _}
import GraphDSL.Implicits._
import akka.stream._

object _6a_graphDSL extends AkkaApp {

  val graph =
    RunnableGraph.fromGraph(GraphDSL.create() { implicit builder =>
      val broadcast = builder.add(Broadcast[Int](2))
      val merge = builder.add(Merge[Int](2))

                         broadcast ~> merge.in(0)
      Source(1 to 10) ~> broadcast
                         broadcast ~> merge.in(1)

      merge ~> Sink.foreach(println)
      ClosedShape
    })

  graph.run()
}