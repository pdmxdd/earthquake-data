package dev.paulmatthews.consumers

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util.Arrays
import java.util.Properties

object GeoEventsOfMagnitudes {

  case class GeoEvent(time: String, latitude: String, longitude: String, depth: String,
                      mag: String, magType: String, nst: String, gap: String, dmin: String,
                      rms: String, net: String, id: String, updated: String, place: String,
                      eventType: String, horizontalError: String, depthError: String,
                      magError: String, magNst: String, status: String,
                      locationSource: String, magSource: String)

  def main(args: Array[String]): Unit = {
    val props: Properties = new Properties()
    props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test")
    props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
    consumer.subscribe(Arrays.asList("geo-events"))

    while (true) {
      val geoEvents: ConsumerRecords[String, String] = consumer.poll(Duration.ofSeconds(10))
      geoEvents.forEach((record: ConsumerRecord[String, String]) => {
        val stringGeoEvent = record.value()
//        println(stringGeoEvent)
        val listGeoEvent = stringGeoEvent.split(",")
        val geoEvent = GeoEvent(
          listGeoEvent(0),
          listGeoEvent(1),
          listGeoEvent(2),
          listGeoEvent(3),
          listGeoEvent(4),
          listGeoEvent(5),
          listGeoEvent(6),
          listGeoEvent(7),
          listGeoEvent(8),
          listGeoEvent(9),
          listGeoEvent(10),
          listGeoEvent(11),
          listGeoEvent(12),
          listGeoEvent(13),
          listGeoEvent(14),
          listGeoEvent(15),
          listGeoEvent(16),
          listGeoEvent(17),
          listGeoEvent(18),
          listGeoEvent(19),
          listGeoEvent(20),
          listGeoEvent(21),
        )
        if (geoEvent.mag.toFloat > 3.9) {
          println(geoEvent)
        }
      })
    }
  }

}
