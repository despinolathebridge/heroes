FROM openjdk:11
MAINTAINER despinola
COPY target/app-0.0.1.jar heroes-app-1.0.0.jar
ENTRYPOINT ["java","-jar","/heroes-app-1.0.0.jar"]