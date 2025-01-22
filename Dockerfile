FROM eclipse-temurin:21

LABEL maintainer="thinh@gmail.com"

WORKDIR /app

COPY build/libs/useridentityservice-0.0.1-SNAPSHOT-plain.jar /app/useridentityservice.jar

ENTRYPOINT ["java", "-jar", "useridentityservice.jar"]