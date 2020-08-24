# spring-boot-sqs-example
Example application to show how to send and consume messages to and from an AWS SQS queue with Spring Boot

# What you will need to run the application
 1. An AWS account
 2. AWS access keys 
 3. Create an SQS queue name (OrderMessages-Q)
 
### How to run it?
Package the application as a Docker image and run as a Docker container. 

To build the Docker image use the following command:
```
$ docker build -t spring-boot-sqs-example .
```

To run the Docker container use the following command and make sure to replace ACCESS_KEY_ID_HERE and SECRET_ACCESS_KEY_HERE with your AWS credentials:

```
$ docker run --env AWS_ACCESS_KEY_ID=ACCESS_KEY_ID_HERE --env AWS_SECRET_ACCESS_KEY=SECRET_ACCESS_KEY_HERE -p 8080:8080 spring-boot-sqs-example 
```
 
### How do I test the application? 

 Using curl make the following post request to place an order which will send the message to queue. 
 ```
 $ curl -i -X POST -H "Content-Type: application/json" -d "{\"reference\" : \"T000000012345\",\"userId\" :\"TEST_USERID\",\"coupon\": \"FIRSTBUY15OFF\",\"products\" : [{ \"productId\": \"PRODUCT1\",\"productName\": \"Baggy Jeans\",\"price\": 26.78,\"code\": \"T0012\"}]}"  http://localhost:8080/api/v1/orders

 ```

Use the following command to see application logs: 

```
$ docker logs CONTAINER_ID --follow

```

In your application logs you should see the order being received and send to the queue, then see the service listening on the queue 
pick the message and process the order.

 