FROM alpine:latest
USER root

RUN apk update
RUN apk fetch openjdk8
RUN apk add openjdk8
ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"
ADD entrypoint.sh /entrypoint.sh
RUN echo "PWD is: $PWD"
ADD /home/runner/work/mvn/mvn/Main.java Main.java
ADD /home/runner/work/mvn/mvn/lib lib
RUN chmod +x /entrypoint.sh
EXPOSE 8080
ENTRYPOINT ["/entrypoint.sh"]
