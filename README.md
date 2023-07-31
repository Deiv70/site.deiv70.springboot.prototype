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
4. Start the Spring Application:
    - With Maven:  
        ```mvn spring-boot:run```  
5. Open the Swagger UI ( **¡¡ check the [.env](.env) file !!** ):  
    ```http://localhost:8080/swagger-ui.html```  
