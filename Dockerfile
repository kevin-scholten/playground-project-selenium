FROM openjdk:11-jre-slim
ADD target/jar .
ENTRYPOINT exec java \
	org.junit.runner.JUnitCore \
	playground.CompleteSuite