FROM openjdk:21-jdk-oracle
COPY ./target/bingo-app-*.jar bingo-app.jar
ENV DATA_SOURCE_URL=jdbc:postgresql://localhost:5432/bingo
ENV DATA_SOURCE_USERNAME=root
ENV DATA_SOURCE_PASSWORD=root
ENTRYPOINT ["java", "-jar", "bingo-app.jar"]