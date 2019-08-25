FROM maven:3.6-jdk-8-alpine as build
ADD ./ app/
WORKDIR /app
RUN mvn install

FROM openjdk:8-jdk-alpine
COPY --from=build /app/target/management-0.0.1-SNAPSHOT.jar management-0.0.1-SNAPSHOT.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","management-0.0.1-SNAPSHOT.jar"]