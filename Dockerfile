FROM openjdk:17-jdk-alpine
#VOLUME /src/main/java
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]