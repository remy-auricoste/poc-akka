package fr.drysoft.pocAkka.event

import akka.NotUsed
import akka.stream.scaladsl.Flow
import javax.inject.Inject

case class EventService @Inject() () {

  def complicatedFunction(number: Int): Boolean = number % 2 == number % 3

  def filterFlow: Flow[Int, Int, NotUsed] = Flow[Int].filter(complicatedFunction)
}