package fr.drysoft.pocAkka.event

import akka.stream.scaladsl.{ Flow, Source }
import fr.drysoft.pocAkka.TestUnitaire
import org.mockito.Matchers._
import org.mockito.Mockito

class EventControllerTest extends TestUnitaire {

  def getService = EventController(
    mock[EventRepository],
    mock[EventService]
  )

  "EventController" should {
    "filter method" should {
      "retrieve data from repository and pass it through EventService" in {
        val service = getService

        Mockito.when(service.eventRepository.findInRange(anyInt, anyInt)).thenReturn(Source(1 to 10))
        Mockito.when(service.eventService.filterFlow).thenReturn(Flow[Int].filter(_ == 2))

        service.filter(0, 10).toSeq should be(Seq(2))
      }
    }
  }
}
