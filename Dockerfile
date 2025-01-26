FROM eclipse-temurin:21

LABEL maintainer="thinh@gmail.com"

WORKDIR /app

COPY ./user-identity-service/build/libs/useridentityservice-0.0.1-SNAPSHOT.jar /app/useridentityservice.jar

ENTRYPOINT ["java", "-jar", "useridentityservice.jar"]