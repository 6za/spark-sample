ARG BASE_IMAGE=6zar/base-spark-scala
ARG BASE_IMAGE_TAG=jvm11-scala2.12-spark3.4.1
FROM ${BASE_IMAGE}:${BASE_IMAGE_TAG}
# Reference: https://github.com/datastax/spark-cassandra-connector#version-compatibility
RUN /opt/spark/bin/spark-submit   --class "Main"  \
                     --packages "com.datastax.spark:spark-cassandra-connector_2.12:3.4.1"   \
                    --master local[*]    /opt/spark/examples/jars/spark-examples_2.12-${SPARK_VERSION}.jar || echo done