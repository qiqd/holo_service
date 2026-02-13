FROM ubuntu:22.04

WORKDIR /app

COPY target/holo_backend ./holo_backend
RUN chmod +x ./holo_backend

EXPOSE 56932

ENTRYPOINT ["./holo-backend"]
