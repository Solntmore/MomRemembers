FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar auth-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/auth-server-0.0.1-SNAPSHOT.jar"]