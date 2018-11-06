package fr.drysoft.pocAkka.http

import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.stream.ActorMaterializer
import fr.drysoft.pocAkka.config.AppConfig
import fr.drysoft.pocAkka.generic.LoggerTrait
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn

case class WebServer @Inject()(
                                appConfig: AppConfig,
                                router: Router
                              )
                              (
                                implicit actorSystem: ActorSystem,
                                actorMaterializer: ActorMaterializer,
                                executionContext: ExecutionContext
                              ) extends LoggerTrait {
  def start = {
    val port = appConfig.port
    val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(router.route, "localhost", port)
      .withErrorLogged
    logger.info(s"Server online at http://localhost:$port/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => actorSystem.terminate()) // and shutdown when done
  }
}
