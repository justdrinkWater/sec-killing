FROM williamyeh/java8:latest
VOLUME /tmp
ADD sk-order-1.0.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 9002
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
