FROM ubuntu:22.04

WORKDIR /app

COPY target/holo-service ./holo-service
RUN chmod +x ./holo-service

EXPOSE 56932

ENTRYPOINT ["./holo-service"]
