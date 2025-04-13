# Notification Microservice

A Spring Boot microservice for managing notifications in the application. It supports web push notifications via OneSignal and stores notification data in a MySQL database. Designed for a microservices architecture with Eureka service discovery and containerized using Docker.

**Built with:**
- Spring Boot 3.4.3
- Java 17
- MySQL
- OkHttp (HTTP requests)
- OneSignal (web push notifications)
- Maven (dependency management)

## Prerequisites

Ensure you have these installed:
- **Java 17**: For running the application.
- **Maven 3.8+**: For building and managing dependencies.
- **Docker**: For containerization.
- **Docker Desktop**: Optional, for easier container management.
- **OneSignal Account**: For web push (requires `app-id` and `api-key`).

## Getting Started

### Clone the Repository

```bash
git clone <repository-url>
cd notification
```
## Authors

- [Bennari Mohamed](https://github.com/MohamedBennari)