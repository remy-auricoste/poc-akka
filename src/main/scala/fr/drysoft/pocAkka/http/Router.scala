package fr.drysoft.pocAkka.http

import akka.http.scaladsl.model.sse.ServerSentEvent
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import fr.drysoft.pocAkka.event.EventController
import javax.inject.Inject
import akka.http.scaladsl.marshalling.sse.EventStreamMarshalling._
import scala.concurrent.duration._

case class Router @Inject() (eventController: EventController)(implicit materializer: ActorMaterializer) {

  val route: Route =
    path("filter") {
      post {
        parameter("min".as[Int], "max".as[Int]) { (min, max) =>
          eventController.filter(min, max)
          complete(s"received filter request from $min to $max")
        }
      }
    } ~
      path("events") {
        get {
          complete {
            eventController.broadcastSource
              .map(number => s"incoming number $number")
              .map(ServerSentEvent(_))
              .keepAlive(1.second, () => ServerSentEvent.heartbeat)
          }
        }
      }
}
