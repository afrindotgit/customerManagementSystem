FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
ADD target/customerManagementSystem-0.0.1-SNAPSHOT.jar /customerManagmentSystem.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","/customerManagmentSystem.jar"]