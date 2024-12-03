FROM openjdk:17
EXPOSE 8080
COPY /target/demo-dockp-api.jar demo-dockp-api.jar
ENTRYPOINT ["java", "-jar", "/demo-dockp-api.jar"]