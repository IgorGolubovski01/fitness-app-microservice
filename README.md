

# Fitness Microservices Platform

**An AI-powered fitness tracking and analytics platform built with modern microservices architecture and Spring Cloud.**

## 🎯 Overview

Fitness Microservices is a production-grade cloud-native platform that enables users to track their fitness activities and receive AI-powered personalized recommendations. Built with Spring Boot and Spring Cloud, it demonstrates enterprise microservices patterns including service discovery, centralized configuration management, API gateway routing, event-driven architecture, and OAuth2 authentication.

## 🏗️ Architecture

The platform follows a **microservices architecture** with distributed components:

```
┌─────────────────────────────────────────────────────────┐
│             Client (with Keycloak JWT)                  │
└────────────────────┬────────────────────────────────────┘
                     │
         ┌───────────▼──────────────┐
         │   API Gateway (8080)     │
         │  - JWT Validation        │
         │  - User Auto-Sync        │
         │  - Load Balancing        │
         └───────────┬──────────────┘
                     │
        ┌────────────┼────────────┐
        │            │            │
   ┌────▼─┐   ┌─────▼──┐   ┌────▼────────┐
   │User  │   │Activity│   │AI Service   │
   │Svc   │   │Service │   │(8083)       │
   │(8081)│   │(8082)  │   └─────┬───────┘
   └──────┘   └────┬───┘         │
        MySQL      │        ┌─────▼──────┐
                   │        │ Gemini API │
                   │        └────────────┘
                   │
              ┌────▼────────────┐
              │  RabbitMQ       │
              │  Message Broker │
              └─────────────────┘
        
        ┌──────────────────┐
        │ Eureka Server    │
        │ Service Registry │
        └──────────────────┘
        
        ┌──────────────────┐
        │ Config Server    │
        │ (8888)           │
        └──────────────────┘
```

## 🚀 Key Features

- **User Management** - Registration, profile management, and Keycloak OAuth2 integration
- **Activity Tracking** - Support for 10+ fitness activity types with custom metrics
- **AI-Powered Recommendations** - Real-time personalized fitness analysis using Google Gemini API
- **Asynchronous Processing** - Event-driven architecture with RabbitMQ for non-blocking operations
- **Service Discovery** - Netflix Eureka for dynamic service registration and health checks
- **Centralized Configuration** - Spring Cloud Config Server for environment-specific settings
- **API Gateway** - Single entry point with authentication, routing, and load balancing
- **Multi-Database Support** - MongoDB for activities/recommendations, MySQL for user data

## 🛠️ Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Language** | Java | 21+ |
| **Framework** | Spring Boot | 4.0.3 |
| **Cloud** | Spring Cloud | 2025.1.0 |
| **Service Discovery** | Eureka | Netflix |
| **Configuration** | Spring Cloud Config | Server |
| **API Gateway** | Spring Cloud Gateway | WebFlux |
| **Message Broker** | RabbitMQ | Latest |
| **Databases** | MongoDB, MySQL | Latest |
| **Authentication** | OAuth2/Keycloak | Latest |
| **AI Model** | Google Gemini | v1beta |
| **Utilities** | Project Lombok | Latest |

## 📦 Microservices

### 1. **Eureka Server** (Port 8761)
Service registry and discovery server. All microservices self-register for dynamic lookup and health monitoring.

### 2. **Config Server** (Port 8888)
Centralized configuration management. Stores service-specific properties including database URLs, API keys, and RabbitMQ credentials.

### 3. **API Gateway** (Port 8080)
- Single entry point for all client requests
- JWT validation via Keycloak
- Auto-synchronizes new users from OAuth2 claims
- Routes requests to appropriate services with load balancing
- Built with reactive Spring WebFlux

### 4. **User Service** (Port 8081)
Manages user profiles and authentication metadata.
- **Database**: MySQL
- **Features**: User registration, profile retrieval, Keycloak integration
- **Endpoints**: `/api/user/**`

### 5. **Activity Service** (Port 8082)
Handles fitness activity tracking and persistence.
- **Database**: MongoDB
- **Features**: Support for running, walking, cycling, swimming, weight training, yoga, HIIT, cardio, stretching, and custom activities
- **Messaging**: Publishes activities to RabbitMQ for AI processing
- **Endpoints**: `/api/activities/**`

### 6. **AI Service** (Port 8083)
Generates AI-powered fitness recommendations.
- **Database**: MongoDB
- **AI Engine**: Google Gemini Flash API
- **Features**: Real-time activity analysis, personalized suggestions, safety guidelines, improvement recommendations
- **Async Pattern**: Consumes messages from RabbitMQ, processes asynchronously
- **Endpoints**: `/api/recommendations/**`

## 🔄 Event Flow

1. **Client** sends tracked activity to API Gateway with JWT token
2. **API Gateway** validates JWT, auto-syncs user if needed, routes to Activity Service
3. **Activity Service** validates user exists, saves to MongoDB, publishes to RabbitMQ
4. **AI Service** listens on RabbitMQ queue, calls Google Gemini API for analysis
5. **AI Service** saves recommendations to MongoDB
6. **Client** retrieves recommendations via recommendation endpoints

## 🔐 Security

- **OAuth2/OIDC**: Keycloak-based authentication at the gateway
- **JWT Validation**: All requests require valid JWT tokens
- **Automatic User Provisioning**: New users auto-registered from OAuth2 claims
- **Centralized Configuration**: Sensitive data (API keys, credentials) managed in Config Server

## 🏛️ Architecture Patterns

- ✅ Microservices Pattern
- ✅ API Gateway Pattern
- ✅ Service Discovery (Eureka)
- ✅ Event-Driven Architecture (RabbitMQ)
- ✅ Database Per Service
- ✅ OAuth2/OIDC Authentication
- ✅ Reactive Web (WebFlux)
- ✅ Centralized Configuration Management
- ✅ Asynchronous Message Processing
- ✅ Load Balancing with Service Discovery

## 📊 Data Models

**Users**: Stored in MySQL with Keycloak integration  
**Activities**: Flexible schema stored in MongoDB  
**Recommendations**: Rich AI-generated insights stored in MongoDB

## 📝 License

This project is licensed under the MIT License.


---

This description covers everything a developer needs to understand your fitness platform at a glance!
