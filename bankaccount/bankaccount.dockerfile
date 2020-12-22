FROM maven:3-alpine
WORKDIR /bankaccount
COPY pom.xml .
COPY src ./src
RUN mvn install
RUN mvn spring-boot:run