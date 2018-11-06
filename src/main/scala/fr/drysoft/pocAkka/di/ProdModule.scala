package fr.drysoft.pocAkka.di

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject.{ Binder, Module }
import com.typesafe.config.ConfigFactory
import fr.drysoft.pocAkka.config.AppConfig

import scala.concurrent.ExecutionContext

class ProdModule extends Module {
  implicit val system = ActorSystem()
  val materializer: ActorMaterializer = ActorMaterializer()
  val executionContext: ExecutionContext = system.dispatcher

  override def configure(binder: Binder): Unit = {
    commonConf(binder)
    binder.bind(classOf[AppConfig]).toInstance(AppConfig(ConfigFactory.load()))
  }

  def commonConf(binder: Binder): Unit = {
    binder.bind(classOf[ActorSystem]).toInstance(system)
    binder.bind(classOf[ActorMaterializer]).toInstance(materializer)
    binder.bind(classOf[ExecutionContext]).toInstance(executionContext)
  }
}
