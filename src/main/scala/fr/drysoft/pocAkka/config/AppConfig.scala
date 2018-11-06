package fr.drysoft.pocAkka.config

import java.util.concurrent.TimeUnit

import com.typesafe.config.{ Config, ConfigFactory }
import fr.drysoft.pocAkka.generic.LoggerTrait

import scala.concurrent.duration.FiniteDuration
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
  private val getDuration: String => FiniteDuration = getValue { config =>
    key =>
      FiniteDuration(config.getDuration(key).toMillis, TimeUnit.MILLISECONDS)
  }

  // ensures that all config keys are valid
  val port = getInt("port")
  val sseProducerBuffer = getInt("sse.producer-buffer")
  val sseSinkBuffer = getInt("sse.sink-buffer")
  val sseHeartbeatInterval = getDuration("sse.heartbeat-interval")
}
