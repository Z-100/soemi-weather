FROM openjdk:17-alpine
RUN mkdir /app
COPY ./build/libs/com.soemiwoers.soemi-woers-all.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
