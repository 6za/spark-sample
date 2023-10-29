# spark-sample

Sample of Multi Host Spark cluster deployment with Docker

# Base image for scala based spark applications

Hosted at: https://hub.docker.com/r/6zar/base-spark-scala


## Setup Base image

- [Dockerfile](./docker/spark-base/Dockerfile)

# Sample Applications to extract data in scala

## Cassandra

```bash 
docker build ./docker/spark-base/ -t 6zar/base-spark-scala:jvm11-scala2.13-spark3.4.1-temp
# Warm of cache of jars
docker build ./docker/spark-cassandra --build-arg="BASE_IMAGE_TAG=jvm11-scala2.13-spark3.4.1-temp" -t test-deps:latest
# Test of cache
docker build ./docker/spark-cassandra --build-arg="BASE_IMAGE_TAG=jvm11-scala2.13-spark3.4.1-cassandra" 
```

References: 
- https://datastax.github.io/spark-cassandra-connector/ApiDocs/3.4.1/connector/com/datastax/spark/connector/index.html

- https://github.com/datastax/spark-cassandra-connector/#version-compatibility

### Tests

```bash 
docker build -f scala/app/Dockerfile .  -t spark-cassandra-test
docker compose -f docker-compose-test-cassandra.yaml up 
```

## Postgres

```bash 
# Warm of cache of jars
docker build ./docker/spark-postgres -t test-deps-postgres:latest
```

### Tests

```bash 
docker build -f scala/app/Dockerfile .  -t spark-postgres-test --build-arg "BASE_IMAGE_TAG=jvm11-scala2.13-spark3.4.1-postgres" --build-arg "PROJECTFOLDER=postgres-app"
docker compose -f docker-compose-test-postgres.yaml up 
```

## S3

```bash 
# Warm of cache of jars
docker build ./docker/spark-s3 -t test-deps-s3:latest
```

### Tests

```bash 
docker build -f scala/app/Dockerfile .  -t spark-s3-test --build-arg "BASE_IMAGE_TAG=jvm11-scala2.13-spark3.4.1-s3" --build-arg "PROJECTFOLDER=s3-app"
docker compose -f docker-compose-test-s3.yaml up 
```

Some other tags will be produced to help with warm cache for dependencies

