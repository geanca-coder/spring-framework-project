FROM eclipse-temurin:17-jdk-alpine
WORKDIR /spring-boot-demo
EXPOSE 8000
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-demo/app.jar","--spring.profiles.active=prod"]
