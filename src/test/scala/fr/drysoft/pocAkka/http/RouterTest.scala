package fr.drysoft.pocAkka.http

import akka.http.scaladsl.server.Route
import fr.drysoft.pocAkka.TestUnitaire

class RouterTest extends TestUnitaire {

  val route: Route = injector.getInstance[Router].route

  "The service" should {
    "add a job and broadcast response via SSE" in {
      Post("/filter?min=0&max=3") ~> route ~> check {
        responseAs[String] should be("received filter request from 0 to 3")
      }
      // I do not know how to test that. Test lib seems not to be coded for SSE. Manually tested.
      //      Get("/events") ~> Route.seal(route) ~> check {
      //        responseAs[String] should be("")
      //      }
    }
  }
}
