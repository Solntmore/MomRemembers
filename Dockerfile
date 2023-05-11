FROM amazoncorretto:11
COPY target/*.jar remembers-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/remembers-0.0.1-SNAPSHOT.jar"]
