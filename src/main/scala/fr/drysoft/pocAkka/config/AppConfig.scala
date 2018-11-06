package fr.drysoft.pocAkka.config

import com.typesafe.config.{Config, ConfigFactory}
import fr.drysoft.pocAkka.generic.LoggerTrait

import scala.util.control.NonFatal

case class AppConfig() extends LoggerTrait {
  lazy val config: Config = ConfigFactory.load()

  private def getValue[T](getter: Config => String => T): String => T = key => {
    val value = try {
      getter(config)(key)
    } catch {
      case NonFatal(ex) => throw new IllegalStateException(s"could not find config key $key")
    }
    logger.debug(s"read config $key : $value")
    value
  }

  private val getString: String => String = getValue[String](_.getString)
  private val getInt: String => Int = getValue[Int](_.getInt)

  // ensures that all config keys are valid
  val port = getInt("port")
}
