# Ghomeleon

## Description

Ghomeleon is a web application developed in Kotlin using the Spring Boot framework. It is a CRUD application built with clean architecture and DDD (Domain-Driven Design) principles.

## Functionality

Ghomeleon provides the following functionality:

- Game Management: Ghomeleon allows storing and managing information about games. This includes details such as game title, description, reviews, and platform releases.
- Game Release Management: Ghomeleon enables tracking and updating information about game releases. This includes release dates, platforms.
- Game Platform Management: Ghomeleon supports storing and managing information about game platforms. This includes platform names, releaseDates, manufacturers.

With these features, Ghomeleon provides a comprehensive solution for storing and updating information about games, game releases, and game platforms. This allows users to easily manage and organize their gaming-related data within the application.

## Requirements

To run the application, you will need the following components:

- Java Development Kit (JDK) version 8 or above - to run integration tests
- Docker
- Docker Compose

## Installation and Setup

1. Clone the Ghomeleon repository to your local machine
2. Start docker compose for files docker-compose.yml and zabbix-docker-compose.yml

## Configuration

Ghomeleon utilizes the following technologies and tools that can be configured:

- Testcontainers: allows running isolated Docker containers for testing.
- Logback: a logging framework, logging configuration can be modified in the `logback-spring.xml` file.
- Zabbix: monitoring and alerting.
- Graylog: centralized log storage and analysis.
- Prometheus and Grafana: monitoring and metrics visualization systems, configuration can be modified in the `prometheus.yml` and `datasource.yml` files.
- PostgreSQL: database connection settings can be modified in the `application.properties` file.
- Adminer: a tool for administering the database.

## Testing

You can run the integration tests using the following command:

`./gradlew testInt`

## License

Ghomeleon is licensed under The GNU General Public License v3.0. For more information, see the `LICENSE` file.