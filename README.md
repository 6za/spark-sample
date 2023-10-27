# spark-sample

Sample of Multi Host Spark cluster deployment with Docker

# Base image for scala based spark applications

Hosted at: https://hub.docker.com/r/6zar/base-spark-scala


## Setup Base image

- [Dockerfile](./docker/spark-base/Dockerfile)

# Sample Applications to extract data in scala

```bash 
docker build ./docker/spark-base/ -t 6zar/base-spark-scala:jvm11-scala2.13-spark3.4.1-temp
# Warm of cache of jars
docker build ./docker/spark-cassandra --build-arg="BASE_IMAGE_TAG=jvm11-scala2.13-spark3.4.1-temp" -t test-deps:latest
# Test of cache
docker build ./docker/spark-cassandra --build-arg="BASE_IMAGE_TAG=latest" --build-arg="BASE_IMAGE=test-deps"
```


Some other tags will be produced to help with warm cache for dependencies