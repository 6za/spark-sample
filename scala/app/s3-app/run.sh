#!/bin/bash
time /opt/spark/bin/spark-submit \
  --class "$CLASSNAME" \
  --driver-memory 8g \
  --executor-memory 3g \
  --num-executors 3 \
  --packages "com.amazonaws:aws-java-sdk:1.11.534","org.apache.hadoop:hadoop-aws:3.2.2" \
  --master local[*] \
  /app/app.jar  