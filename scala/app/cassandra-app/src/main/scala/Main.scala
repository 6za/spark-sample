import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
import com.datastax.spark.connector.cql.CassandraConnectorConf
import org.apache.spark.sql.cassandra._
import org.apache.spark.storage.StorageLevel

//References: https://github.com/datastax/spark-cassandra-connector/blob/master/doc/data_source_v1.md
object Main {
  def main(args: Array[String]): Unit = {
    val cassandraHost = sys.env.getOrElse ("CASSANDRA_HOST", "db-cassandra")
    val s3InFolder = sys.env.getOrElse("IN_FOLDER", "in")
    val s3OutFolder = sys.env.getOrElse("OUT_FOLDER", "out")
    val spark: SparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("CassandraApp")
      .getOrCreate()

    spark.setCassandraConf("MainCluster", CassandraConnectorConf.ConnectionHostParam.option(cassandraHost))

    val table = "products"
    val tempFilename = s"/$s3OutFolder/$table.parquet"

    val sourceDF = spark
      .read
      .format("org.apache.spark.sql.cassandra")
      .options(Map("table" -> table, "keyspace" -> "data", "cluster" -> "MainCluster"))
      .load
    sourceDF.persist(StorageLevel.MEMORY_ONLY)
    sourceDF
      .repartition(10)
      .write.parquet(tempFilename)
    sourceDF.unpersist()
    val postDumpDF = spark.read.format("parquet").load(tempFilename)

    postDumpDF.write
      .format("org.apache.spark.sql.cassandra")
      .options(Map("table" -> "products_v2", "keyspace" -> "data", "cluster" -> "MainCluster"))
      .mode("append")
      .save()
    
    println(s"Total records extracted($table): ${postDumpDF.count}")
  }
}