package fr.drysoft.pocAkka

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{ Sink, Source }
import fr.drysoft.pocAkka.config.AppConfig
import fr.drysoft.pocAkka.di.CustomInjector
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{ Matchers, WordSpec }

import scala.concurrent.duration._
import scala.concurrent.{ Await, Future }

trait TestUnitaire extends WordSpec with Matchers with MockitoSugar with ScalatestRouteTest {
  implicit val actorSystem: ActorSystem = TestUnitaire.actorSystem
  implicit override val materializer: ActorMaterializer = TestUnitaire.materializer

  val injector = TestUnitaire.injector
  val appConfig = injector.getInstance[AppConfig]

  def await[T](future: Future[T]): T = Await.result(future, 5 seconds)

  implicit class SourceWrapper[T](source: Source[T, NotUsed]) {
    def toSeq: Seq[T] = await(source.runWith(Sink.seq))
  }
}

object TestUnitaire {
  val injector = CustomInjector()

  implicit val actorSystem: ActorSystem = ActorSystem("TestUnitaire")
  val materializer: ActorMaterializer = ActorMaterializer()
}
