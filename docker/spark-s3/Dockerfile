ARG BASE_IMAGE=6zar/base-spark-scala
ARG BASE_IMAGE_TAG=jvm11-scala2.12-spark3.4.1
FROM ${BASE_IMAGE}:${BASE_IMAGE_TAG}
RUN /opt/spark/bin/spark-submit   --class "Main"  \
                     --packages "com.amazonaws:aws-java-sdk:1.11.534","org.apache.hadoop:hadoop-aws:3.2.2"   \
                    --master local[*]    /opt/spark/examples/jars/spark-examples_2.12-${SPARK_VERSION}.jar || echo done