FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} delivery-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/delivery-service.jar"]

