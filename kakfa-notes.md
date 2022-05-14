# Steps to get the Kafka Server Running & Topic Created

Thank you [Kafka Quickstart!](https://kafka.apache.org/documentation/#quickstart)

1. download kafka: very easy a tarball
1. run server
  1. `bin/zookeeper-server-start.sh config/zookeeper.properties`: note that this will soon no longer be required by Kafka. (I'd love to know more about that. What the heck is a zookeeper?)
  1. `bin/kafka-server-start.sh config/server.properties`: broker is a term I've seen about Kafka
1. create a topic: `bin/kafka-topics.sh --create --topic geo-events --bootstrap-server localhost:9092`

There were more `quickstart`y steps that lead through some of the basics of adding events, and reading the events directly from Kafka on the command line. Which is cool, but not what I'm focused on right now.

I do want to remember:

- describe topic: `bin/kafka-topics.sh --describe --topic geo-events --bootstrap-server localhost:9092`
- read events (in topic) from beginning: `bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092`
- `/tmp/kafka-logs`
- `/tmp/zookeeper`