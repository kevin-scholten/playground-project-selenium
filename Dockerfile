#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src .
COPY pom.xml .
RUN mvn -f pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build target/playground-project-selenium-1.0-SNAPSHOT.jar /usr/local/lib/demo.jar
ADD junit.jar .
ENTRYPOINT exec java -cp /usr/local/lib/demo.jar:junit.jar org.junit.runner.JUnitCore com.example.playgroundprojectselenium.MainPageTest
EXPOSE 4447