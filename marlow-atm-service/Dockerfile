FROM openjdk:11

COPY target/*.jar marlow-atm-service-latest.jar

ENV ARTIFACT_NAME=marlow-atm-service-latest.jar

ENV J_ARGS=""

#DB initialization
COPY src/main/resources/*.sql /docker-entrypoint-initdb.d/

#MS Port
EXPOSE 8092

ENTRYPOINT [ "sh", "-c", "java $J_ARGS -Djava.security.egd=file:/dev/./urandom -jar /${ARTIFACT_NAME}" ]