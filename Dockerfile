FROM openjdk:11-jre-slim
COPY target/playground-project-selenium-1.0-SNAPSHOT-fat-tests.jar .
ENTRYPOINT exec java -cp playground-project-selenium-1.0-SNAPSHOT-fat-tests.jar org.junit.runner.JUnitCore com.example.playgroundprojectselenium.MainPageTest