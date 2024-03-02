# Project Documentation

## Overview

This project is a chat application that allows users to send and receive messages in real-time. It is divided into multiple modules, each serving a specific purpose within the application's architecture. The key modules include:

- **API Module**: Handles HTTP requests, allowing users to interact with the application.
- **Consumer Module**: Listens for messages on a Kafka topic and processes them accordingly.
- **Common Module**: Contains shared entities and repositories.

## Implemented Requirements

1. **User Account Creation**: Users can create accounts by providing a unique nickname.
2. **Send Message**: Users can send messages to other users identified by their user IDs.
3. **View Received Messages**: Users can view messages they have received.
4. **View Sent Messages**: Users can view messages they have sent.
5. **View Messages from a Specific User**: Users can view messages received from a particular user.
6. **Real-time Message Processing**: Messages are sent to a Kafka topic and processed by the consumer module in real-time.

## How to Run and Use the Application

### Prerequisites

- Java JDK
- Maven
- Kafka and Zookeeper

### Running the Application

1. **Start Kafka and Zookeeper**:
   Ensure Kafka and Zookeeper are running. You can start them using the Kafka command-line tools or through a Docker container.

2. **Start the API Module**:
   Navigate to the API module directory and run:
   ```sh
   mvn spring-boot:run
   ```
   This starts the Spring Boot application which hosts the REST endpoints.

3. **Start the Consumer Module**:
   In a separate terminal, navigate to the consumer module directory and run:
   ```sh
   mvn spring-boot:run
   ```
   This starts the consumer service that listens for messages on the Kafka topic.

Absolutely, here's the updated README section with the instructions for running Kafka and PostgreSQL using Docker Compose, along with curl examples for each endpoint.

## Running Kafka and PostgreSQL

To start Kafka and PostgreSQL, you need to have Docker and Docker Compose installed on your machine. Once installed, you can run the following command:

```sh
docker-compose -f deployment/docker-compose.yml up -d
```

This command will start Kafka and PostgreSQL containers in the background.

## Interacting with the Application using curl

### Create a User

To create a user, you can use the following curl command:

```sh
curl -X POST 'http://localhost:8080/users?nickname=john_doe'
```

Replace `"john_doe"` with the desired nickname.

### Send a Message

To send a message, use the following curl command:

```sh
curl -X POST http://localhost:8080/messages -H 'Content-Type: application/json' -d '{"senderId": 1, "receiverId": 2, "content": "Hello from John to Jane"}'
```

Adjust `senderId`, `receiverId`, and `"Hello from John to Jane"` as needed.

### View Received Messages

To view received messages for a user with ID `2`, execute:

```sh
curl -X GET http://localhost:8080/messages/received/2
```

### View Sent Messages

To view sent messages from a user with ID `1`, use:

```sh
curl -X GET http://localhost:8080/messages/sent/1
```

### View Messages from a Specific User

To view messages received by user `2` from user `1`, use:

```sh
curl -X GET http://localhost:8080/messages/2/from/1
```

These curl commands provide a simple way to interact with your chat application's REST API endpoints. Adjust the user IDs and message content as necessary to fit your testing or interaction needs.

## Improvements

- **Authentication**: Implement user authentication to secure the application and ensure that users can only access their messages.
- **WebSockets**: Use WebSockets for real-time bidirectional communication between clients and the server, improving the real-time messaging feature.
- **Scalability**: Implement partitioning and consumer groups in Kafka to enhance the scalability of the message processing mechanism.
- **Unit and Integration Tests**: Expand the test coverage to include more unit and integration tests, ensuring robustness and reliability.
- **Dockerization**: Containerize the application using Docker to simplify deployment and ensure consistency across different environments.
- **User Interface**: Develop a user-friendly frontend to interact with the application, improving user experience.
- **Message Encryption**: Implement encryption for messages to enhance privacy and security.

This README provides a basic outline for understanding, running, and using the chat application. It also outlines potential improvements to enhance the application's functionality and user experience.