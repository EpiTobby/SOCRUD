FROM openjdk:17.0-jdk

COPY target/socrud-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT java -Dserver.port=8080 -Dspring.security.user.name=${DEFAULT_USERNAME} -Dspring.security.user.password=${DEFAULT_PASSWORD} -jar app.jar