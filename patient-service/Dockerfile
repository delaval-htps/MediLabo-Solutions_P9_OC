FROM openjdk:17-alpine
RUN apk add --no-cache curl
LABEL application-name="patient-service"
LABEL maintainer="delaval.htps@gmail.com"
LABEL version="V0.0.2"
VOLUME [ "/main-app" ]
ADD target/patient-service-0.0.2.jar patient-service.jar
EXPOSE 8083
ENTRYPOINT [ "java","-jar","/patient-service.jar" ]