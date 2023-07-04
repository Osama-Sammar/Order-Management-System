# Order Management Application
The Order Management Application is a Spring Boot-based application that provides functionalities to manage orders, customers, and products.

# Getting Started
Prerequisites
Make sure you have the following software installed on your machine:

Apache Maven,
Java JDK 11,
Docker 

# Clone the Repository
Clone the repository to your local machine using the following command:

bash
git clone https://github.com/Osama-Sammar/Order-Management-System.git

# Build the Application
Open your preferred IDE (e.g., IntelliJ IDEA) and import the project. Alternatively, you can use the command line.

Navigate to the project directory and run the following command to build the application:

bash
mvn clean package

This command will compile the source code, run tests, and package the application into a JAR file.

# Run the Application
You can run the application using Maven or by executing the JAR file.

Using Maven
Open a terminal or command prompt and navigate to the project directory. Run the following command:

bash
mvn spring-boot:run

The application will start and be accessible at http://localhost:8080.

Using the JAR file
In the terminal or command prompt, navigate to the project directory and run the following command:

bash
java -jar target/Order-Management-System.jar
The application will start and be accessible at http://localhost:8080.

# Swagger Documentation
The application provides Swagger documentation, which can be accessed at http://localhost:8080/swagger-ui/. The Swagger documentation provides details about the available endpoints and their usage.

# Docker Image 
If you prefer running the application in a Docker container, follow the steps below.

Build the Docker Image
Make sure Docker is installed and running on your machine.

Open a terminal or command prompt and navigate to the project directory. Run the following command:

bash
docker build -t Order-Management-System .

This command will create a Docker image named "Order-Management-System" using the Dockerfile in the project directory.

Run the Docker Container
Once the image is built successfully, you can run the Docker container using the following command:

bash
docker run -p 8080:8080 Order-Management-System
This command starts a container from the "Order-Management-System" image and maps port 8080 of the container to port 8080 of the host machine.

The application should now be running in the Docker container, and you can access the Swagger documentation at http://localhost:8080/swagger-ui/.





