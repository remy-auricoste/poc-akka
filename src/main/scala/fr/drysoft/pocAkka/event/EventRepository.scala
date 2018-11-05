package fr.drysoft.pocAkka.event

import akka.NotUsed
import akka.stream.scaladsl.Source
import javax.inject.Inject

case class EventRepository @Inject() () {
  def findInRange(min: Int, max: Int): Source[Int, NotUsed] = Source(min to max)
}
