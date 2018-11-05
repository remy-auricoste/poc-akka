package fr.drysoft.pocAkka.event

import fr.drysoft.pocAkka.TestUnitaire

class EventRepositoryTest extends TestUnitaire {

  def getService = EventRepository()

  val service = getService

  "EventRepository" should {
    "retrieve method" should {
      "return first integers" in {
        service.findInRange(0, 3).toSeq === Seq(0, 1, 2, 3)
      }
    }
  }
}
