FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
EXPOSE 8089:8089
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/soemi-woers-0.0.1.jar /app/soemi-woers.jar
ENTRYPOINT ["java","-jar","/app/soemi-woers.jar"]
