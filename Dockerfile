FROM maven:3.8.7 as build
COPY . .
RUN mvn -B clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build ./target/*.jar gutendexbooks.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","gutendexbooks.jar"]