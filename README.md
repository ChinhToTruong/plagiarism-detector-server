# [PLAGIARISM DETECTOR SERVER](https://github.com/ChinhToTruong/plagiarism-detector-server)
***
## About
***
* This project will detect plagiarism in your document

## Requirements
***
* JDK 17
* Maven 3

## Technical stack
***
* Spring Boot 3
* Spring Security 6
* Redis
* MySQL
* Kafka
* Docker
* ELK
* Grafana, Prometheus
* Unit test

## Package application
***
- Clean and package application
```bash
    mvn clean package -P test|dev|prod
```
## Run application
***
- Run
```bash
   mvn spring-boot:run   
```
- Docker
```bash
    docker-compose up -d --build
```
