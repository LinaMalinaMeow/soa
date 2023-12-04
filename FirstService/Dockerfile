#define base docker image
FROM openjdk:17
ADD target/FirstService-1.0-SNAPSHOT.jar FirstService.jar
ENTRYPOINT ["java", "-jar", "FirstService.jar"]
