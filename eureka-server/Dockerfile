FROM openjdk:17-alpine
RUN apk add --no-cache curl
LABEL application-name="eureka-server"
LABEL maintainer="delaval.htps@gmail.com"
LABEL version="V0.0.2"
VOLUME [ "/main-app" ]
ADD target/eureka-server-0.0.2.jar eureka-server.jar
EXPOSE 8082
ENTRYPOINT [ "java","-jar","/eureka-server.jar" ]	
	
