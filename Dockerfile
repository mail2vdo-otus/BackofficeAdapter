FROM anapsix/alpine-java

MAINTAINER vorobiev.org
RUN apk --update add curl
COPY ./target/BackofficeAdapter-1.0-SNAPSHOT.jar ./opt/

ENTRYPOINT ["java"]

CMD ["-jar", "./opt/BackofficeAdapter-1.0-SNAPSHOT.jar"]

EXPOSE 8100