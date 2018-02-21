#!/bin/sh
mvn clean package && docker build -t br.edu.ifpb/security-messenger .
docker rm -f security-messenger || true && docker run -d -p 8080:8080 -p 4848:4848 --name security-messenger br.edu.ifpb/security-messenger 


# docker run -p 5432:5432 -d --env POSTGRES_USER=postgres --env POSTGRES_DB=security-messenger --env POSTGRES_PASSWORD=postgres --name securitydb postgres
# docker run -p 27017:27017 -d --name mongosec mongo