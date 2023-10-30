#!/bin/bash
time /opt/spark/bin/spark-submit \
  --class "$CLASSNAME" \
  --driver-memory 8g \
  --executor-memory 3g \
  --num-executors 3 \
  --packages "mysql:mysql-connector-java:8.0.33" \
  --master local[*] \
  /app/app.jar  