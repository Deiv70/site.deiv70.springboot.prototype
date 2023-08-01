# site.deiv70.springboot.prototype
Spring Boot 3 Prototype Proyect: [ DDD + Hexagonal + Data JPA + Swagger (OpenAPI) + REST Web ]

## Index
<!-- TOC -->
* [site.deiv70.springboot.prototype](#sitedeiv70springbootprototype)
  * [Index](#index)
  * [Getting Started](#getting-started)
<!-- TOC -->

## Getting Started

First steps:
1. Clone this repository:  
    ```git clone https://github.com/Deiv70/site.deiv70.springboot.prototype.git```  
2. Make your own Environment file from the [example.env](example.env) template:  
    ```cp example.env .env```  
3. Start the needed Containers with Docker:  
    ```docker-compose --env-file .env -f compose.yaml up -d```  
4. Start the Spring Application (_**dev** profile_ will be used by default):
    - With Maven:  
        ```mvn clean spring-boot:run```  
      - To use a different profile, append `-P [ dev | pre | pro ]`:  
        ```mvn clean spring-boot:run -P pre```  
5. Open the Swagger UI ( **¡¡ check the [.env](.env) file !!** ):  
    ```http://localhost:8080/swagger-ui```  
6. To execute the tests:  
    ```mvn clean verify```  
    - To make a JaCoCo coverage report:  
        1. ```mvn clean verify -Pcoverage```  
        2. To see results, open: [target/site/jacoco/index.html](target/site/jacoco/index.html)  
    - To make a SonarQube analysis:  
        ```mvn clean verify sonar:sonar -Pcoverage -Dsonar.host.url=${SONAR_HOST_URL} -Dsonar.token=${SONAR_TOKEN}```  
