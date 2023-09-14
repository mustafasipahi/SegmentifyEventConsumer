FROM maven:3.8.6-amazoncorretto-8 AS maven_build
WORKDIR /segmentify_consumer
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:8
WORKDIR /segmentify_consumer
COPY --from=maven_build /segmentify_consumer/target/Segmentify-Consumer-1.0-SNAPSHOT.jar my_consumer_app.jar
ENTRYPOINT ["java","-jar","my_consumer_app.jar"]
EXPOSE 8080