import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

trait AkkaApp extends App {
  implicit val system = ActorSystem("akka")
  implicit val ec = system.dispatcher
}
