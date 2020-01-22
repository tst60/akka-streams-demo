import akka.stream.scaladsl.{Sink, _}
import GraphDSL.Implicits._
import akka.stream._

object _6b_graphDSL_lb extends AkkaApp {

  val graph =
    RunnableGraph.fromGraph(GraphDSL.create() { implicit builder =>
      val lb = builder.add(Balance[Int](2))
      val merge = builder.add(Merge[Int](2))

      val workerA = Flow[Int].map(i => {println(s"A processing: $i"); i * 5})
      val workerB = Flow[Int].map(i => {println(s"B processing: $i"); i * 5})

                         lb ~> workerA ~> merge.in(0)
      Source(1 to 10) ~> lb;              merge ~> Sink.foreach(println)
                         lb ~> workerB ~> merge.in(1)

      ClosedShape
    })

  graph.run()
}
