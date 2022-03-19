FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/data-gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} data-gateway.jar
EXPOSE 5001
ENTRYPOINT ["sh", "-c", "java -jar /data-gateway.jar"]