FROM sbtscala/scala-sbt:eclipse-temurin-jammy-11.0.17_8_1.9.0_2.12.18 as builder
# Add Dependencies for PySpark
#RUN apt-get update && apt-get install -y curl vim wget software-properties-common ssh net-tools ca-certificates python3 python3-pip python3-numpy python3-matplotlib python3-scipy python3-pandas python3-simpy
RUN apt-get update && apt-get install -y curl software-properties-common ssh net-tools ca-certificates && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

RUN update-alternatives --install "/usr/bin/python" "python" "$(which python3)" 1

# Fix the value of PYTHONHASHSEED
# Note: this is needed when you use Python 3.3 or greater
ENV SPARK_VERSION=3.4.1 \
HADOOP_VERSION=3 \
SPARK_HOME=/opt/spark \
PYTHONHASHSEED=1 \
SCALA_VERSION=2.12


# Download and uncompress spark from the apache archive
# https://spark.apache.org/downloads.html
# 2.12 Scala Support
RUN wget --no-verbose -O apache-spark.tgz "https://archive.apache.org/dist/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz" \
&& mkdir -p /opt/spark \
&& tar -xf apache-spark.tgz -C /opt/spark --strip-components=1 \
&& rm apache-spark.tgz

# Non 2.12 Scala Support
# RUN wget --no-verbose -O apache-spark.tgz "https://archive.apache.org/dist/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}-scala${SCALA_VERSION}.tgz" \
# && mkdir -p /opt/spark \
# && tar -xf apache-spark.tgz -C /opt/spark --strip-components=1 \
# && rm apache-spark.tgz



# Apache spark environment
FROM builder as apache-spark

WORKDIR /opt/spark

ENV SPARK_MASTER_PORT=7077 \
SPARK_MASTER_WEBUI_PORT=8080 \
SPARK_LOG_DIR=/opt/spark/logs \
SPARK_MASTER_LOG=/opt/spark/logs/spark-master.out \
SPARK_WORKER_LOG=/opt/spark/logs/spark-worker.out \
SPARK_WORKER_WEBUI_PORT=8080 \
SPARK_WORKER_PORT=7000 \
SPARK_MASTER="spark://spark-master:7077" \
SPARK_WORKLOAD="master"

EXPOSE 8080 7077 6066

RUN mkdir -p $SPARK_LOG_DIR && \
touch $SPARK_MASTER_LOG && \
touch $SPARK_WORKER_LOG && \
ln -sf /dev/stdout $SPARK_MASTER_LOG && \
ln -sf /dev/stdout $SPARK_WORKER_LOG

COPY start-spark.sh /
#WARM cache of common libraries: 
#RUN /opt/spark/bin/spark-submit   --class "Main"   --packages "org.apache.spark:spark-sql-kafka-0-10_2.12:$SPARK_VERSION","com.amazonaws:aws-java-sdk:1.11.534","org.apache.hadoop:hadoop-aws:3.2.2","com.squareup.okhttp3:okhttp:4.9.0"   --master local[*]    /opt/spark/examples/jars/spark-examples_2.13-${SPARK_VERSION}.jar || echo done

CMD ["/bin/bash", "/start-spark.sh"]
