FROM eclipse-temurin:19-jdk-alpine

RUN apk update
RUN apk add maven

VOLUME /tmp
ARG JAR_FILE
COPY . .
RUN mvn package
ENTRYPOINT ["mvn","spring-boot:run"]