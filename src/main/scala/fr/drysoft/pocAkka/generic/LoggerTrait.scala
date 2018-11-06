package fr.drysoft.pocAkka.generic

import org.slf4j.LoggerFactory

import scala.concurrent.{ ExecutionContext, Future }

trait LoggerTrait {
  val logger = LoggerFactory.getLogger(this.getClass)

  implicit class FutureWrapper[T](future: Future[T])(implicit executionContext: ExecutionContext) {
    def withErrorLogged: Future[T] = future.recover {
      case ex: Exception =>
        logger.error(ex.getMessage, ex)
        throw ex
    }
  }
}
