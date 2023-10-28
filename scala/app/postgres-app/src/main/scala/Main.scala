import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
import org.apache.spark.storage.StorageLevel

import java.util.Properties

object Main {
  def main(args: Array[String]): Unit = {
    val postgresHost = sys.env.getOrElse("DB_HOST", "db-postgres")
    val postgresPort = sys.env.getOrElse("DB_HOST", "5432")
    val postgresDBName = sys.env.getOrElse("DB_NAME", "postgres")
    val postgresUser = sys.env.getOrElse("DB_USER", "postgres")
    val postgresPassword = sys.env.getOrElse("DB_PASSWORD", "postgres")
    val s3InFolder = sys.env.getOrElse("IN_FOLDER", "in")
    val s3OutFolder = sys.env.getOrElse("OUT_FOLDER", "out")
    val spark: SparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("PostgresApp")
      .getOrCreate()

    val table = "products"
    val tempFilename = s"/$s3OutFolder/$table.parquet"

    val properties = new Properties()
    properties.put("user", postgresUser)
    properties.put("password", postgresPassword)

    val sourceDF = spark.sqlContext.read
      .option("driver", "org.postgresql.Driver")
      .jdbc(s"jdbc:postgresql://$postgresHost:$postgresPort/$postgresDBName", table, properties)

    sourceDF.persist(StorageLevel.MEMORY_ONLY)
    sourceDF
      .repartition(10)
      .write.parquet(tempFilename)
    sourceDF.unpersist()

    val postDumpDF = spark.read.format("parquet").load(tempFilename)
    postDumpDF.write
      .option("driver", "org.postgresql.Driver")
      .mode("overwrite")
      .option("truncate","true")
      .jdbc(s"jdbc:postgresql://$postgresHost:5432/postgres", "products_v2", properties)

    println(s"Total records extracted($table): ${sourceDF.count}")

  }
}