package fr.drysoft.pocAkka.event

import akka.stream.scaladsl.Source
import fr.drysoft.pocAkka.TestUnitaire

class EventServiceTest extends TestUnitaire {

  def getService = EventService()

  "EventService" should {
    "isPrime" should {
      "return true if the number is prime" in {
        val service = getService
        Seq(1, 2, 3, 5, 7, 11, 13, 17, 19).foreach { prime =>
          service.isPrime(prime) should be(true)
        }
        service.isPrime(4) should be(false)
      }
      "return true if the number is prime (high number)" in {
        val service = getService
        service.isPrime(100) should be(false)
        service.isPrime(101) should be(true)
      }
    }

    "filterPrimes" should {
      "return only prime numbers" in {
        val service = getService
        Source(1 to 20).via(service.filterPrimes).toSeq should be(Seq(1, 2, 3, 5, 7, 11, 13, 17, 19))
      }
    }
  }
}
