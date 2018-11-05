package fr.drysoft.pocAkka

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{ Sink, Source }
import org.scalatest.{ Matchers, WordSpec }

import scala.concurrent.duration._
import scala.concurrent.{ Await, Future }

trait TestUnitaire extends WordSpec with Matchers {
  implicit val actorSystem: ActorSystem = ActorSystem("TestUnitaire")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  def await[T](future: Future[T]): T = Await.result(future, 5 seconds)

  implicit class SourceWrapper[T](source: Source[T, NotUsed]) {
    def toSeq: Seq[T] = await(source.runWith(Sink.seq))
  }
}

object TestUnitaire {
}
