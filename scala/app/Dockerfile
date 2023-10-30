ARG BASE_IMAGE=6zar/base-spark-scala
ARG BASE_IMAGE_TAG=jvm11-scala2.12-spark3.4.1-cassandra
FROM ${BASE_IMAGE}:${BASE_IMAGE_TAG} as app-build
ARG PROJECTFOLDER=cassandra-app
ADD scala/app/${PROJECTFOLDER}/ /app
WORKDIR /app
RUN sbt package 


FROM ${BASE_IMAGE}:${BASE_IMAGE_TAG}
ARG PROJECTFOLDER=cassandra-app
ADD scala/app/${PROJECTFOLDER}/*.sh /app/
WORKDIR /app
COPY --from=app-build /app/target/scala-2.12/${PROJECTFOLDER}_2.12-0.1.0-SNAPSHOT.jar /app/app.jar
RUN chmod +x /app/run.sh
SHELL [ "/bin/bash" ]
CMD  /app/run.sh
