FROM openjdk:8-jdk
ADD target/auditChecklist-0.0.1-SNAPSHOT.jar auditAuth.jar
EXPOSE 8400
ENTRYPOINT ["java","-jar","/auditAuth.jar"]