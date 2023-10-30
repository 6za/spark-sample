ARG BASE_IMAGE=6zar/base-spark-scala
ARG BASE_IMAGE_TAG=jvm11-scala2.12-spark3.4.1
FROM ${BASE_IMAGE}:${BASE_IMAGE_TAG}
RUN /opt/spark/bin/spark-submit   --class "Main"  \
                     --packages "org.postgresql:postgresql:42.6.0"   \
                    --master local[*]    /opt/spark/examples/jars/spark-examples_2.12-${SPARK_VERSION}.jar || echo done