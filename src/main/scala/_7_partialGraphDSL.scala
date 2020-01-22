import akka.stream._
import akka.stream.scaladsl._
import GraphDSL.Implicits._

object _7_partialGraphDSL extends AkkaApp {

  val flow =
    Flow.fromGraph(GraphDSL.create() { implicit builder =>
      val broadcast = builder.add(Broadcast[Int](2))
      val merge = builder.add(Merge[Int](2))

      broadcast ~> merge.in(0)
      broadcast ~> merge.in(1)

      FlowShape(broadcast.in, merge.out)
    })

  Source(1 to 10).via(flow).runForeach(println)
}
