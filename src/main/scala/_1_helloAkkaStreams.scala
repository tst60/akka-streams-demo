import akka.stream.scaladsl.Source

object _1_helloAkkaStreams extends AkkaApp {
  Source(1 to 10)
    .map(_ * 2)
    .runForeach(println)

  Source(1 to 10).takeWhile(_ < 10)
}
