package fr.drysoft.pocAkka.config

import fr.drysoft.pocAkka.TestUnitaire

class AppConfigTest extends TestUnitaire {

  "AppConfig" should {
    "be loaded from test.conf" in {
      appConfig.port === 1234
    }
  }
}
