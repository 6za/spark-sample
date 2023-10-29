#!/bin/bash
time /opt/spark/bin/spark-submit \
  --class "$CLASSNAME" \
  --driver-memory 8g \
  --executor-memory 3g \
  --num-executors 3 \
  --packages "com.datastax.spark:spark-cassandra-connector_2.13:3.4.1" \
  --master local[*] \
  /app/app.jar  