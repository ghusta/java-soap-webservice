FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/lib/ lib/

COPY target/soap-webservice-server-*.jar app.jar

EXPOSE 8080

CMD ["java", "-cp", "app.jar:lib/*", "-Dserver.port=8080", "com.example.ws.ServerPublisher"]
