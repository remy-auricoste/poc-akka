package fr.drysoft.pocAkka.http

import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.stream.ActorMaterializer
import fr.drysoft.pocAkka.di.CustomInjector

import scala.concurrent.{ ExecutionContext, Future }
import scala.io.StdIn

object WebServer extends App {
  def handle(ex: Exception): Unit = {
    System.err.println(ex.getMessage)
    ex.printStackTrace()
  }

  try {
    val injector = CustomInjector()

    val router = injector.getInstance[Router]

    implicit val actorSystem: ActorSystem = injector.getInstance[ActorSystem]
    implicit val materializer: ActorMaterializer = injector.getInstance[ActorMaterializer]
    implicit val executionContext: ExecutionContext = injector.getInstance[ExecutionContext]

    val port = 9000
    val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(router.route, "localhost", port)
      .recover {
        case ex: Exception =>
          handle(ex)
          throw ex
      }
    println(s"Server online at http://localhost:$port/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => actorSystem.terminate()) // and shutdown when done
  } catch {
    case ex: Exception => handle(ex)
  }

}
