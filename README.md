# Change Data Capture - using Debezium for Cache eviction

## What is CDC 
Change data capture (CDC) is the process of capturing changes made at the data source and applying them throughout the enterprise applications.

### Scenario could be Cache invalidation 

Suppose you have two microservices in your application,say Customer service(MS1) and Invoice (MS2). 

Invoice service (MS2) need to have some detail of Customer(MS1) the possible way to achieve this is via invoking rest api of customerDetails(MS1) from Invoice(MS2). Each time getting invoking api to get the details is not recommended for optimization lets say we introduce cache at MS2 level. Cache would help in reducing unnecesary api calls. 

But what if customer details like address get update then how Invoice microservice will be able to get updated information?

##Yes you are right through CDC.

There are various way to capture the data changes, here i am going to use apache-kafka but you could also try apache-pulsar. 

we will explore Debezium with apache-kafka-connect which will export data changes event to kafka via kafka-connect.

To capture event we need to set up 
1. Zookeeper
2. Kafka
3. kafka-connect
4. Database Mysql

Start Mysql
```
docker run -it --rm --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=debezium -e MYSQL_USER=mysqluser -e MYSQL_PASSWORD=mysqlpw debezium/example-mysql:1.4
```

Start zookeeper
```
docker run -it --rm --name zookeeper -p 2181:2181 -p 2888:2888 -p 3888:3888 debezium/zookeeper:1.4
```

start kafka
```
docker run -it --rm --name kafka -p 9092:9092 --link zookeeper:zookeeper debezium/kafka:1.4
```

start kafka-connect 
```
docker run -it --rm --name connect -p 8083:8083 -e GROUP_ID=1 -e CONFIG_STORAGE_TOPIC=my_connect_configs -e OFFSET_STORAGE_TOPIC=my_connect_offsets -e STATUS_STORAGE_TOPIC=my_connect_statuses --link zookeeper:zookeeper --link kafka:kafka --link mysql:mysql debezium/connect:1.4
```

Check Kafka connect service 
```$xslt
curl -H "Accept:application/json" localhost:8083/
{"version":"2.6.0","commit":"cb8625948210849f"} 
```

List kafka connect connectors
```
curl -H "Accept:application/json" localhost:8083/connectors/
[]
```

Registering connector to the database

```$
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d '{ "name": "inventory-connector", "config": { "connector.class": "io.debezium.connector.mysql.MySqlConnector", "tasks.max": "1", "database.hostname": "mysql", "database.port": "3306", "database.user": "debezium", "database.password": "dbz", "database.server.id": "184054", "database.server.name": "dbserver1", "database.include.list": "inventory", "database.history.kafka.bootstrap.servers": "kafka:9092", "database.history.kafka.topic": "dbhistory.inventory" } }'
```

Verify Connectors
```
curl -i -X GET -H "Accept:application/json" localhost:8083/connectors/inventory-connector
HTTP/1.1 200 OK
Date: Wed, 27 Jan 2021 16:02:43 GMT
Content-Type: application/json
Content-Length: 535
Server: Jetty(9.4.33.v20201020)

{"name":"inventory-connector","config":{"connector.class":"io.debezium.connector.mysql.MySqlConnector","database.user":"root","database.server.id":"184054","tasks.max":"1","database.hostname":"mysql","database.password":"debezium","database.history.kafka.bootstrap.servers":"kafka:9092","database.history.kafka.topic":"dbhistory.inventory","name":"inventory-connector","database.server.name":"dbserver1","database.port":"3306","database.include.list":"inventory"},"tasks":[{"connector":"inventory-connector","task":0}],"type":"source"}
```

Watch Kafka topic
```
docker run -it --rm --name watcher --link zookeeper:zookeeper --link kafka:kafka debezium/kafka:1.4 watch-topic -a -k dbserver1.inventory.customers
```

Thanks for reading, happy learning.
