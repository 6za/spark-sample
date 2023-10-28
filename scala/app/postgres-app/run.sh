#!/bin/bash
time /opt/spark/bin/spark-submit \
  --class "$CLASSNAME" \
  --driver-memory 8g \
  --executor-memory 3g \
  --num-executors 3 \
  --packages "org.postgresql:postgresql:42.6.0","org.apache.spark:spark-sql-kafka-0-10_2.12:3.4.1","com.amazonaws:aws-java-sdk:1.11.534","org.apache.hadoop:hadoop-aws:3.2.2" \
  --master local[*] \
  /app/app.jar  