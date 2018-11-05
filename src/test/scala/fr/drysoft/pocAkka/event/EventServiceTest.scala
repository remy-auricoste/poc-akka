package fr.drysoft.pocAkka.event

import akka.stream.scaladsl.Source
import fr.drysoft.pocAkka.TestUnitaire

class EventServiceTest extends TestUnitaire {

  def getService = EventService()

  "EventService" should {
    "complicatedFunction" should {
      "return if number has same modulo from 2 and 3" in {
        val service = getService
        service.complicatedFunction(1) should be(true)
        service.complicatedFunction(2) should be(false)
        service.complicatedFunction(4) should be(false)
        service.complicatedFunction(6) should be(true)
      }
    }

    "filter" should {
      "return only numbers with same modulo for 2 and 3" in {
        val service = getService
        Source(1 to 20).via(service.filterFlow).toSeq should be(Seq(1, 6, 7, 12, 13, 18, 19))
      }
    }
  }
}
