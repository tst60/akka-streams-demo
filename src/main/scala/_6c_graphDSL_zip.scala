import akka.stream.scaladsl.{Sink, _}
import GraphDSL.Implicits._
import akka.stream._

object _6c_graphDSL_zip extends AkkaApp {

  val graph =
    RunnableGraph.fromGraph(GraphDSL.create() { implicit builder =>
      val broadcast = builder.add(Broadcast[Int](2))
      val zip = builder.add(Zip[Int, String])

      val workerA = Flow[Int].map(_ * 5)
      val workerB = Flow[Int].map(i => s"Processed $i")

                         broadcast ~> workerA ~> zip.in0
      Source(1 to 10) ~> broadcast;              zip.out ~> Sink.foreach(println)
                         broadcast ~> workerB ~> zip.in1

      ClosedShape
    })

  graph.run()
}