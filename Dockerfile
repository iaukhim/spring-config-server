FROM eclipse-temurin:17-jdk-alpine
RUN apk add curl
RUN mkdir -p /root/.ssh
WORKDIR ./app
ARG JAR_FILE=./target/config-server-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]