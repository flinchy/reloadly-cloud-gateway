FROM openjdk:11

EXPOSE 9191

ADD ./build/libs/*.jar api-gateway.jar

ENTRYPOINT ["java","-jar","/api-gateway.jar"]
