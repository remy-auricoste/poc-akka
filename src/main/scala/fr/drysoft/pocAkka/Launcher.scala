package fr.drysoft.pocAkka

import fr.drysoft.pocAkka.di.CustomInjector
import fr.drysoft.pocAkka.generic.LoggerTrait
import fr.drysoft.pocAkka.http.WebServer

object Launcher extends App with LoggerTrait {
  logger.info("starting...")

  try {
    val injector = CustomInjector()

    val webServer = injector.getInstance[WebServer]
    webServer.start
  } catch {
    case ex: Exception => logger.error(ex.getMessage, ex)
  }
  logger.info("ending.")
}
