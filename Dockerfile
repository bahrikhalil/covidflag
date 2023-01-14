FROM openjdk:11

ARG JAR_FILE=./target/docker_app.jar

ADD $JAR_FILE app.jar


ENTRYPOINT ["java","-jar","/app.jar"]
