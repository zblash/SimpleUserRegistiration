<h1 align="center">Welcome to SimpleUserRegistiration 👋</h1>
<p>
</p>

> This project for boilerplate for Spring MVC authentication proccesses

## Author

👤 **Yusuf Can Celik**

* Github: [@zblash](https://github.com/zblash)

## Tech Stack
* Java 8
* Postgresql
* Spring Boot
* Spring Security
* Thymeleaf
* Spring Data(Hibernate/JPA)
* Lombok
* Bootstrap

# Installation
## Project
 **With Maven/ Java**

 Firstly you have to install maven packages
```sh
mvn install
```
 Then run this command for start application
```sh
java -jar target/demo-0.0.1-SNAPSHOT.jar
```
 Or you can use easily Spring Boot CLI
```sh
mvn spring-boot:run
```
## Database
 You can use docker-compose file for PostgreSQL and pgAdmin docker files run
```sh
docker-compose up
```
> If you don't want to use docker or you have already database then you have to edit application.yml files under resources files and if you want to change database then you have to change dependency for your database. For e-mail service you have to edit application.yml files with your e-mail service's informations

# Workflow
## User Registration 
* The user registers with a form
* E-mail validation - This step sends an email to the user who has filled in that they want to register containing a activation code the completed e-mail address
* Create user - Automatic steps that create the user

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_