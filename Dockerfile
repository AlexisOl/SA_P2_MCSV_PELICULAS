FROM bellsoft/liberica-openjdk-alpine:21.0.3

WORKDIR /app

COPY target/mcsv-movies.jar /app/mcsv-movies.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "mcsv-movies.jar"]
