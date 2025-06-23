# Use a base JDK image
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the project (using Maven Wrapper)
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Run the app
CMD ["java", "-jar", "target/*.jar"]
