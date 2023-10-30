import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

object Main {
  def main(args: Array[String]): Unit = {

    val redisHost = sys.env.getOrElse("CASSANDRA_HOST", "redis-host")
    val s3InFolder = sys.env.getOrElse("IN_FOLDER", "in")
    val s3OutFolder = sys.env.getOrElse("OUT_FOLDER", "out")

    val inputFile = f"/$s3InFolder/products.csv"

    val spark: SparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("MySQLApp")
      .getOrCreate()

      //.config("spark.redis.host", redisHost)
      //.config("spark.redis.port", "6379")

    val sourceDF = spark.read.format("csv").option("header", "true").load(inputFile)
    println(s"Total records extracted($inputFile): ${sourceDF.count}")
    println("Hello world!")
  }
}