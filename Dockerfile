FROM openjdk:21-rc-oracle
COPY ./target/bingo-app-*.jar bingo-app.jar
ENTRYPOINT ["java", "-jar", "bingo-app.jar"]