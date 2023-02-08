FROM openjdk:11

COPY target/*.jar marlow-config-server-service-latest.jar

ENV ARTIFACT_NAME=marlow-config-server-service-latest.jar

ENV J_ARGS=""

#MS Port
EXPOSE 8090

ENTRYPOINT [ "sh", "-c", "java $J_ARGS -Djava.security.egd=file:/dev/./urandom -jar /${ARTIFACT_NAME}" ]
