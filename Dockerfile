#
# Package stage
#
FROM openjdk:11-jre-slim
ADD junit.jar .
ADD target/playground-project-selenium-1.0-SNAPSHOT-tests.jar .
#ENTRYPOINT exec java -cp playground-project-selenium-1.0-SNAPSHOT-tests.jar:junit.jar org.junit.runner.JUnitCore com.example.playgroundprojectselenium.MainPageTest