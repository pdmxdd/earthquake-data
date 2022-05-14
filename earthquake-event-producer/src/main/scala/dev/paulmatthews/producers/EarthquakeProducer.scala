package dev.paulmatthews.producers

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import java.util.Properties


object EarthquakeProducer {
  val BootstrapServer: String = "localhost:9200"
  val Topic: String = "geo-events"

  def main(args: Array[String]): Unit = {
    val properties = getProperties(BootstrapServer)
    val producer = new KafkaProducer[String, String](properties)

    val record = new ProducerRecord[String, String](Topic, "2022-05-14T03:02:28.795Z,60.1376,-151.2598,55.4,1.3,ml,,,,0.3,ak,ak02265ntan3,2022-05-14T03:12:39.241Z,12 km SE of Clam Gulch, Alaska,earthquake,,2.3,,,automatic,ak")

    producer.send(record)

    producer.close()
  }

  def getProperties(bootstrapServer: String): Properties = {
    val properties = new Properties
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer)
    properties.setProperty(ProducerConfig.ACKS_CONFIG, "1")
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)

    properties
  }
}
