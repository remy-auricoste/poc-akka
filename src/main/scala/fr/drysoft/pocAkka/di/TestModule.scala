package fr.drysoft.pocAkka.di
import com.google.inject.Binder
import com.typesafe.config.ConfigFactory
import fr.drysoft.pocAkka.config.AppConfig

class TestModule extends ProdModule {
  override def configure(binder: Binder): Unit = {
    super.configure(binder)
    binder.bind(classOf[AppConfig]).toInstance(AppConfig(ConfigFactory.load("test")))
  }
}
