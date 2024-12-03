## Overview
This is an e-commerce platform built using **Spring Boot** for the backend and other relevant technologies. The application supports user authentication, product management, order processing, and payment handling. It's designed to simulate the typical features found in modern e-commerce websites.

## Features
- **User Authentication**: Users can register, log in, and manage their accounts securely.
- **Product Management**: Admins can add, update, and delete products, and manage product categories.
- **Order Management**: Users can add products to their cart, place orders, and track their order status.
- **Payment Integration**: The platform integrates a basic payment processing system with status tracking.
- **Review System**: Users can rate and review products.

## Technologies Used
- **Backend**: Spring Boot, Spring Data JPA, Spring Security, Spring Validation
- **Database**: MySQL
- **Mapping**: MapStruct for DTO-to-Entity mapping
- **Authentication**: JWT (JSON Web Token)
- **Mail**: Spring Boot Starter Mail (for notifications)
- **Logging**: SLF4J with Logback
- **Testing**: Spring Boot Starter Test

## Prerequisites
Before running the project, ensure that you have the following installed:
- **Java 21** or higher
- **MySQL** (for the database)
- **Maven** (for building the project)

- ## Build and Run the Project
- mvn spring-boot:run
