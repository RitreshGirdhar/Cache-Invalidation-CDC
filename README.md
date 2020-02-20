# WIP - Cache-Invalidation-CDC

## What is CDC 
Change data capture (CDC) is the process of capturing changes made at the data source and applying them throughout the enterprise applications.

### Scenario could be Cache invalidation 
Suppose you have two microservices in your application,say Customer(MS1) and Invoice (MS2). 
Invoice service (MS2) need to have some detail of Customer(MS1) the possible way to achieve this is via invoking rest api of customerDetails(MS1) from Invoice(MS2). Each time getting invoking api to get the details is not recommended for optimization lets say we introduce cache at MS2 level. Cache would help in reducing unnecesary api calls. 

But what if Customer details like address get update then how Invoice microservice will be able to get updated information?? 
##Yes you are right through CDC.

There are various way to capture the data changes, here i am going to use apache-kafka but you could also try apache-pulsar. 

we will explore Debezium with apache-kafka-connect which will export data changes event to kafka via kafka-connect.

To capture event we need to set up 
1. Zookeeper
2. Kafka
3. kafka-connect
4. Database postgress

Start postgres
```$xslt
docker run -d --name postgres -v /Users/ritgirdh/Desktop/tools/postgres:/var/lib/postgresql/data -p 54320:5432 postgres:11
```

Start zookeeper
```$xslt
docker run -it --rm --name zookeeper -p 2181:2181 -p 2888:2888 -p 3888:3888 debezium/zookeeper:0.9
```

start kafka
```$xslt
docker run -it --rm --name kafka -p 9092:9092 --link zookeeper:zookeeper debezium/kafka:0.9
```

start kafka-connect 
```
docker run -it --rm --name connect -p 8083:8083 -e GROUP_ID=1 -e CONFIG_STORAGE_TOPIC=customer-changes -e OFFSET_STORAGE_TOPIC=customer-changes-offset -e STATUS_STORAGE_TOPIC=customer_changes_statuses --link zookeeper:zookeeper --link postgres:postgres --link kafka:kafka debezium/connect:0.9
```

Now bind kafka-connector with postgres
```
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d '{"name":"customer-connector","config":{"connector.class":"io.debezium.connector.postgresql.PostgresConnector","tasks.max":"1","database.hostname":"10.151.24.253","database.port":"54320","database.user":"postgres","database.password":"","database.dbname":"postgres","database.server.name":"postgres","schema.whitelist":"customer","database.history.kafka.bootstrap.servers":"kafka:9092","database.history.kafka.topic":"dbhistory.customer"}}'
``` 

Check connector configuration 
```$xslt
curl -H "Accept:application/json" localhost:8083/connectors/customer-connector
```

Watch Kafka topic
```$xslt
docker run -it --name watcher --rm --link zookeeper:zookeeper --link kafka:kafka debezium/kafka:0.9 watch-topic -a -k customer-changes
```

bind Postgres with kafka-connect

```$xslt
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d '{"name":"customer-service-connector","config":{"connector.class":"io.debezium.connector.postgresql.PostgresConnector","tasks.max":"1","database.hostname":"10.151.24.253","database.port":"54320","database.user":"postgres","database.password":"","database.dbname":"postgres","database.server.name":"postgres","schema.whitelist":"customer","database.history.kafka.bootstrap.servers":"kafka:9092","database.history.kafka.topic":"customer-service-history"}}'
```

```
curl -H "Accept:application/json" localhost:8083/connectors/customer-service-connector
```

WIP....
