FROM openjdk:17.0-jdk

COPY target/socrud-0.0.1-SNAPSHOT.jar app.jar

ENV spring.security.user.name ${DEFAULT_USERNAME}
ENV spring.security.user.password ${DEFAULT_PASSWORD}

ENTRYPOINT java -Dserver.port=8080 -jar app.jar