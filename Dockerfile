From maven:3.9-eclipse-temurin-21 AS bulid
COPY . .
RUN MVN clean package -DskipTests

FROM eclipse-temurin:21-alpine
COPY --from=bulid /target/*.jar/ demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]
