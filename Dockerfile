FROM ubuntu:22.04

WORKDIR /app

COPY target/holo-backend-1.0.0 ./holo-backend
RUN chmod +x ./holo-backend

EXPOSE 56932

ENTRYPOINT ["./holo-backend"]