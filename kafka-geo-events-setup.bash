kakfa_dir=/home/paul/personal/kafka/kafka_2.13-3.1.0

$kafka_dir/bin/zookeeper-server-start.sh $kafka_dir/config/zookeeper.properties & > zookeeper-server.pid

sleep 4

$kafka_dir/bin/kafka-server-start.sh $kafka_dir/config/server.properties > kafka-server.pid

sleep 4

$kafka_dir/bin/kafka-console-consumer.sh --create --topic geo-events --bootstrap-server localhost:9092
