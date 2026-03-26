FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/*.jar app.jar

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

ENTRYPOINT ["java","-jar","/app/app.jar"]
