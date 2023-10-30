#!/bin/bash
time /opt/spark/bin/spark-submit \
  --class "$CLASSNAME" \
  --driver-memory 8g \
  --executor-memory 3g \
  --num-executors 3 \
  --packages "com.redislabs:spark-redis_2.12:3.0.0" \
  --master local[*] \
  /app/app.jar  