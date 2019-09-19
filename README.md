# Cache-Invalidation-CDC

## What is CDC 
Change data capture (CDC) is the process of capturing changes made at the data source and applying them throughout the enterprise applications.

### Scenario could be Cache invalidation 
Suppose you have two microservices in your application,say Customer(MS1) and Invoice (MS2). 
Invoice service (MS2) need to have some detail of Customer(MS1) the possible way to achieve this is via invoking rest api of customerDetails(MS1) from Invoice(MS2). Each time getting invoking api to get the details is not recommended for optimization lets say we introduce cache at MS2 level. Cache would help in reducing unnecesary api calls. 

But what if Customer details like address get update then how Invoice microservice will be able to get updated information?? 
##Yes you are right through CDC.

There are various way to capture the data changes, here i am going to use apache-kafka but you could also try apache-pulsar. 

we will explore Debezium with apache-kafka-connect which will export data changes event to kafka via kafka-connect.

