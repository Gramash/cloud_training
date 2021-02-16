FROM gradle:6.8.2-jdk11-openj9 AS buildStage
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build

FROM adoptopenjdk/openjdk11:alpine-jre
RUN mkdir /app
COPY --from=buildStage /home/gradle/src/build/libs/*.jar /app/spring-boot-kotlin.jar
ENTRYPOINT ["java", "-jar", "/app/spring-boot-kotlin.jar"]