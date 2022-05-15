package dev.paulmatthews

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import java.util.Properties

object App {
  
  def main(args : Array[String]) {
    val properties = new Properties
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties.setProperty(ProducerConfig.ACKS_CONFIG, "1")
//    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
//    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    val producer = new KafkaProducer[String, String](properties)

    val record = new ProducerRecord[String, String]("geo-events", "2022-05-14T03:02:28.795Z,60.1376,-151.2598,55.4,1.3,ml,,,,0.3,ak,ak02265ntan3,2022-05-14T03:12:39.241Z,12 km SE of Clam Gulch, Alaska,earthquake,,2.3,,,automatic,ak")

    producer.send(record)

    producer.close()
  }

}
