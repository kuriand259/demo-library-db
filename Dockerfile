# Use a JDK base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy all files to container
COPY . .

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Use `sh -c` so wildcard works for unknown JAR name
CMD ["sh", "-c", "java -jar target/*.jar"]
