# Spring Micro Services
Its used to demonstate a Loan Processing Application.

## Overview
Components designed to accompalish -
* [API Gateway](#api-gateway)
* [Service Registry and Discovery](#service-registry-and-discovery)
* [Authentication Service](#authentication-service)
* [Loan Application](#loan-application)
* [Client UI](#client-ui)

### API Gateway
This is a entry point for client requests, all request are passed through API gateway. Inter microservices communications also been facilitated via API gateway.
* Entry point for all requests
* Authentication and Authorization of all incoming requests
* Acts as agreegator
* Acts as loan balancer of service, if there are more than one instance of any service is deployed in cluster.
* It is one of the microservice itself, registered to Eureka server

### Service Registry and Discovery
Its server using Netflix Eureka. All the micoservices registers them selves to this registry including API gateway.
When client request arrives in API gateway for any resource, API gateway first makes a request for service discovery, and one of the returned list of servers will be used to make a further call by API gateway.
* Registers microservice
* Discovers microservice

### Authentication Service
Its one of the microservice designed to Authenticate User against database.
* Authenticates user
* Issues a JWT token for further communication

### Loan Application
* Its a microservice
* Exposes a CRUD Rest Services for Loan Application

### Client UI
* Designed in Angular
* Allows registered users to login, and apply for loan
* Allows bank managers to take action against loan application ( Approve, Reject, etc...)

### Ports to run on local system
|     Application       |     URL          |    Default Port      | Tools      |
| ------------- | ------------- |--------------|--------------|
| API Gateway | 8765 |  8765     |Netflix Zuul, Ribben Load Balancer, JWT Security|
| Service Registry/Discovery | http://localhost:8761/ | 8761 |Netflix Eureka|
| Authentication Service |http://localhost:8200/  |8200, 8201, 8202, ...|Spring Cloud, Spring Boot, JWT Security|
| Loan Application | http://localhost:8100/  |8100, 8101, 8102|Spring Cloud, Spring Boot |
| Client Application | http://localhost:4200/ | 4200 | Angular |

### Run the applications in following order
* Service Registry
* API Gateway
* Authentication Service
* Loan Application
* Client Application

