FROM public.ecr.aws/l6s9c2z9/my-docker:gradle AS buildStage
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build -x test

FROM public.ecr.aws/l6s9c2z9/my-docker:java11
RUN mkdir /app
COPY --from=buildStage /home/gradle/src/build/libs/*.jar /app/spring-boot-kotlin.jar
ENTRYPOINT ["java", "-jar", "/app/spring-boot-kotlin.jar"]