FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD /var/lib/jenkins/workspace/sfp-build-pipeline/sfda/target/sfda-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8080
