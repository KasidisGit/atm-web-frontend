FROM maven:3-alpine
WORKDIR /atm-web
COPY pom.xml .
COPY src ./src
RUN mvn install
RUN mvn spring-boot:run

