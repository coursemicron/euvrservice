# EU VAT Rates Service 

This project demonstrates a microservice with endpoints which follows industry standard design patterns

## 01 - System requirements to run
* Java 17
* Maven
* Postman
* Git
* Docker

## 02 - Build

Follow below steps for building docker image
* Open terminal at the root directory
* docker build -t euvrservice:1.0 .

## 02 - Run container
* docker run -p 8080:8080 euvrservice:1.0

## 03 - Testing endpoint with Postman
* import postman collection 'evrservice.postman_collection.json'
* execute each endpoints to test


