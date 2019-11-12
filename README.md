# MicroServices



## Services and Ports
|     Application       |     URL          |    Default Port      | Tools      |
| ------------- | ------------- |--------------|--------------|
| API Gateway | 8765 |  8765     |Netflix Zuul, Ribben Load Balancer, JWT Security|
| Service Registry/Discovery | http://localhost:8761/ | 8761 |Netflix Eureka|
| Authentication Service |http://localhost:8200/  |8200, 8201, 8202, ...|Spring Cloud, Spring Boot, JWT Security|
| Loan Application | http://localhost:8100/  |8100, 8101, 8102|Spring Cloud, Spring Boot |
| Client Application | http://localhost:4200/ | 4200 | Angular |


