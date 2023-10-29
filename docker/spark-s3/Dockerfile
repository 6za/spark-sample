ARG BASE_IMAGE=6zar/base-spark-scala
ARG BASE_IMAGE_TAG=jvm11-scala2.13-spark3.4.1
FROM ${BASE_IMAGE}:${BASE_IMAGE_TAG}
# Reference: https://github.com/datastax/spark-cassandra-connector#version-compatibility
RUN /opt/spark/bin/spark-submit   --class "Main"  \
                     --packages "org.apache.spark:spark-sql-kafka-0-10_2.12:$SPARK_VERSION","com.amazonaws:aws-java-sdk:1.11.534","org.apache.hadoop:hadoop-aws:3.2.2","com.datastax.spark:spark-cassandra-connector_2.13:3.4.1"   \
                    --master local[*]    /opt/spark/examples/jars/spark-examples_2.13-${SPARK_VERSION}.jar || echo done