package fr.drysoft.pocAkka.di

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject.{ Binder, Module }

import scala.concurrent.ExecutionContext

case class ProdModule() extends Module {
  implicit val system = ActorSystem()
  val materializer: ActorMaterializer = ActorMaterializer()
  val executionContext: ExecutionContext = system.dispatcher

  override def configure(binder: Binder): Unit = {
    binder.bind(classOf[ActorMaterializer]).toInstance(materializer)
    binder.bind(classOf[ActorSystem]).toInstance(system)
    binder.bind(classOf[ExecutionContext]).toInstance(executionContext)
  }
}
