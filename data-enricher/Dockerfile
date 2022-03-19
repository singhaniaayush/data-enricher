FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/data-enricher-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} data-enricher.jar
EXPOSE 5000
ENTRYPOINT ["sh", "-c", "java -jar /data-enricher.jar"]