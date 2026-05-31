# Step 1: Use Maven + Java 21 to BUILD the jar
FROM maven:3.9-eclipse-temurin-21 AS build

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy pom.xml first (for dependency caching)
COPY pom.xml .

# Step 4: Download all dependencies
RUN mvn dependency:go-offline

# Step 5: Copy your source code
COPY src ./src

# Step 6: Build the jar (skip tests)
RUN mvn clean package -DskipTests

# ─────────────────────────────────────────
# Step 7: New clean image just to RUN the jar
FROM eclipse-temurin:21-jre

# Step 8: Set working directory
WORKDIR /app

# Step 9: Copy the built jar from build stage
COPY --from=build /app/target/user-api-0.0.1-SNAPSHOT.jar app.jar

# Step 10: Expose port
EXPOSE 8080

# Step 11: Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]