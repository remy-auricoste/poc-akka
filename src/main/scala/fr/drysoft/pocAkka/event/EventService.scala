package fr.drysoft.pocAkka.event

import akka.NotUsed
import akka.stream.scaladsl.Flow
import javax.inject.Inject

case class EventService @Inject() () {

  var primes: Set[Int] = Set()

  def isPrime(number: Int): Boolean = {
    if (number == 1) true
    else {
      val lastPrime: Int = primes.lastOption.getOrElse(2)
      val sqrt = Math.sqrt(number)
      if (sqrt > lastPrime) { // init primes if needed
        ((lastPrime + 1) to Math.floor(sqrt).toInt).foreach(isPrime)
      }

      val result: Boolean = primes.forall(number % _ != 0)
      if (result) primes = primes + number
      result
    }
  }

  def filterPrimes: Flow[Int, Int, NotUsed] = Flow[Int].filter(isPrime)
}
