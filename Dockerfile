FROM openjdk:latest
WORKDIR /tmp
COPY ./target/seMethods-1.0-SNAPSHOT.jar /tmp
ENTRYPOINT ["java", "-jar", "seMethods-1.0-SNAPSHOT.jar"]