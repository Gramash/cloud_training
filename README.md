1. In order to run application locally do the following: 
 - execute in terminal:
    1. docker build -t spring-boot-kotlin .
    2. docker run -p 8080:8080 spring-boot-kotlin
- access the app via http://localhost:8080/actuator/health url to check the app is running

2. Url to access application running in aws-cloud 
    - http://3.239.201.57:8080/actuator/info
    - http://3.239.201.57:8080/actuator/health/custom
