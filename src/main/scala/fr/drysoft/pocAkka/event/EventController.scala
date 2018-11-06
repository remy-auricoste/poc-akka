package fr.drysoft.pocAkka.event

import akka.NotUsed
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{ BroadcastHub, Keep, MergeHub, Sink, Source }
import fr.drysoft.pocAkka.config.AppConfig
import javax.inject.Inject

case class EventController @Inject() (
    eventRepository: EventRepository,
    eventService: EventService,
    appConfig: AppConfig)(
        implicit actorMaterializer: ActorMaterializer) {

  val (broadcastSink, broadcastSource) =
    MergeHub.source[Int](perProducerBufferSize = appConfig.sseProducerBuffer)
      .toMat(BroadcastHub.sink(bufferSize = appConfig.sseSinkBuffer))(Keep.both)
      .run()
  // Ensure that the Broadcast output is dropped if there are no listening parties.
  // If this dropping Sink is not attached, then the broadcast hub will not drop any
  // elements itself when there are no subscribers, backpressuring the producer instead.
  broadcastSource.runWith(Sink.ignore)

  def filter(min: Int, max: Int): Source[Int, NotUsed] = {
    val source = eventRepository.findInRange(min, max)
      .via(eventService.filterFlow)
    source.runWith(broadcastSink) // broadcast data to listening SSEs
    source
  }
}
