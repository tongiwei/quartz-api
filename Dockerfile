FROM openjdk:11-jdk-slim
ADD --chown=1000:1000 ./build/libs/quartz-api-0.0.1.jar /app/app.jar
RUN echo "Asia/Shanghai" > /etc/timezone
WORKDIR /app
USER 1000
CMD ["java","-jar","app.jar"]
