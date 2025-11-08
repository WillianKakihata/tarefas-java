FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app
COPY . .

RUN yum install -y ca-certificates && update-ca-trust

RUN mvn clean package -DskipTests

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/target/DockerApp.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
