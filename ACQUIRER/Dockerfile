FROM openjdk:17
WORKDIR /app
EXPOSE 8085
ADD target/acquirer-0.0.1.jar /app/acquirer-0.0.1.jar
ENTRYPOINT ["java","-jar","acquirer-0.0.1.jar"]