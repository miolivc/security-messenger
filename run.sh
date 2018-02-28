#!/bin/sh
mvn clean package
docker build -t seg/security-messenger .

docker run -p 5433:5432 -d --env POSTGRES_USER=postgres --env POSTGRES_DB=security-messenger --env POSTGRES_PASSWORD=postgres --name securitydb postgres
docker run -p 27017:27017 -d --name mongosec mongo
docker run -d -p 8080:8080 -p 4848:4848 --link securitydb:host-pg --link mongosec:host-mongo --name security-messenger seg/security-messenger 
