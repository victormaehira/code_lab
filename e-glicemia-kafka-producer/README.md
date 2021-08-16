docker-compose exec kafka kafka-topics --create --topic glicemias --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181

docker-compose exec kafka kafka-console-consumer --bootstrap-server localhost:29092 --topic glicemias --from-beginning --max-messages 10