## Overview
This is an e-commerce platform built using **Spring Boot** for the backend and other relevant technologies. The application supports user authentication, product management, order processing, and payment handling. It's designed to simulate the typical features found in modern e-commerce websites.

## Features
- **User Authentication**: Users can register, log in, and manage their accounts securely.
-    **Email Verification**: Users receive an email verification link upon registration. Accounts remain inactive until verified. Tokens have an expiration time, and users can request a new verification email if needed.
- **Product Management**: Admins can add, update, and delete products, and manage product categories.
- **Order Management**: Users can add products to their cart, place orders, and track their order status.
- **Payment Integration**: The platform integrates a basic payment processing system with status tracking.
- **Review System**: Users can rate and review products.
- **Reset Password**

## Technologies Used
- **Backend**: Spring Boot, Spring Data JPA, Spring Security, Spring Validation
- **Database**: MySQL
- **Mapping**: MapStruct for DTO-to-Entity mapping
- **Authentication**: JWT (JSON Web Token)
- **Mail**: Spring Boot Starter Mail (for notifications)
- **Logging**: SLF4J with Logback
- **Testing**: Spring Boot Starter Test

## Prerequisites
Java Development Kit (JDK) - Version 21 or later.
Maven - Ensure Maven is installed and added to your PATH.
MySQL Database - MySQL Server should be running.
Postman (optional) - For testing REST APIs.

-## MySQL Database Setup
1-Log in to MySQL:
Open your terminal or MySQL Workbench and log in using your credentials.
mysql -u <your-username> -p
2-Create the Database:
CREATE DATABASE ecommerce_db; 
3-Edit application.properties:
Navigate to the src/main/resources/application.properties file and update the database credentials. Replace <username> and <password> with your MySQL credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.hibernate.ddl-auto=update
4-Database Parameters:
Database Name: ecommerce_db
Host: localhost
Port: 3306
Username and Password: As per your setup.

- ## Build and Run the Project

 cd ecommerce-platform
 2-Build the Project:
 mvn clean install
 3-Run the Application:
 mvn spring-boot:run
 4-Access the Application:
 The backend server will be running on http://localhost:8082.


