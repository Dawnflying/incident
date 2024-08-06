FROM openjdk:8-jdk-alpine
RUN apk update && apk add tini fontconfig ttf-dejavu && \
    apk add tini && \
    echo "Asia/Shanghai" > /etc/timezone
RUN mkdir -p  /usr/src/app && \
    mkdir -p /home/project
COPY incident-managment-0.0.1-SNAPSHOT.jar /usr/src/app

EXPOSE 9098
CMD ["java", "-Xmx600m","-jar","/usr/src/app/incident-managment-0.0.1-SNAPSHOT.jar"]