# Card Management

This project showcases the use of blockchain in Authentication Data Management System for storing the data. In this system, we have three participants, namely HRD Center, Webcash Company, and Cooncon Company.

Audience level : Intermediate Developers

## Features

* Hyperledger Fabric
* Docker
* Docker-Compose

## Application Workflow Diagram

* Generate Certificates and Keys for peers
* Generate Channel Transaction configurations
* Start the blockchain network

## Third Party Librarry

We find that Blockchain can be finicky when it comes to installing Nodes. 

* Docker - v1.13 or higher
* Docker Compose - v1.8 or higher
* Git client


## references







## File structure

```
cardmanangement/
 │
 ├── src/main/java/com/mbanq/
 │   └── cardmanagement
 │       ├── configuration
 │       │  
 │       │
 │       ├── controller
 │       │   └── AuthController.java
 │       │
 │       ├── payload
 │       │   ├── UserDataDTO.java
 │       │   └── UserResponseDTO.java
 │       │
 │       ├── exception
 │       │   ├── CustomException.java
 │       │   └── GlobalExceptionController.java
 │       │
 │       ├── model
 │       │   ├── Role.java
 │       │   └── User.java
 │       │
 │       ├── repository
 │       │   └── UserRepository.java
 │       │
 │       ├── security
 │       │   ├── JwtTokenFilter.java
 │       │   ├── JwtTokenFilterConfigurer.java
 │       │   ├── JwtTokenProvider.java
 │       │   ├── MyUserDetails.java
 │       │   └── WebSecurityConfig.java
 │       │
 │       ├── service
 │       │   └── UserService.java
 │       │
 │       └── CardManagementApplication.java
 │
 ├── src/main/resources/
 │   └── application.properties
 │
 ├── .gitignore
 ├── LICENSE
 ├── mvnw/mvnw.cmd
 ├── README.md
 └── pom.xml
```
