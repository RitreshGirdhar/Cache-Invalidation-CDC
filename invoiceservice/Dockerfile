FROM openjdk:8-jdk-alpine
VOLUME /tmp
#ARG JAR_FILE
ADD target/invoiceservice.jar application.jar
EXPOSE 7070
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","application.jar"]