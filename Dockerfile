FROM openjdk:21
VOLUME /tmp
COPY target/t1-0.0.1-SNAPSHOT.jar t1.jar
ENTRYPOINT ["java","-jar","/t1.jar"]