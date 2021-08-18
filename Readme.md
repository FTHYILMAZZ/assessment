# Technical data

Unit and integration tests were written in the application. Exception handling structure has been established with Advisor method and maven-surefire-plugin has been implemented

# Project Description

This is a garage application. The garage contains 10 slots. After the vehicle has settled, 1 slot is reserved and the customer is given a ticket_id.

## How to access H2 console 

http://localhost:8085/h2-console
```
User Name: sysadmin
Password: aDmiN
```


## How to Run

GarageApplication.java class must be run

```
.\mvnw spring-boot:run
```

## Rest Api Metods And Requests

```
park()  ---> Request example :  {"plate":"07HMN92","color":"WHITE","vehicleType": "TRUCK" } 
leave() ---> TicketID
status() 
``` 

## Postman requests
Postman Collection forwarded but as example.

```
park()  Post ---> http://localhost:8085/garage/park
leave() Delete ---> http://localhost:8085/garage/leave/1
status() Get ---> http://localhost:8085/garage/status
``` 