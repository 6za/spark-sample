import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

object Main {
  def main(args: Array[String]): Unit = {

    val mysqlHost = sys.env.getOrElse("MYSQL_HOST", "db-mysql")
    val s3InFolder = sys.env.getOrElse("IN_FOLDER", "in")
    val s3OutFolder = sys.env.getOrElse("OUT_FOLDER", "out")

    val inputFile = f"/$s3InFolder/products.csv"

    val spark: SparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("MySQLApp")
      .getOrCreate()

    val sourceDF = spark.read.format("csv").option("header", "true").load(inputFile)

    sourceDF.write
      .format("jdbc")
      .mode("overwrite")
      .option("truncate", "true")
      .option("driver", "com.mysql.cj.jdbc.Driver")
      .option("url", s"jdbc:mysql://$mysqlHost:3306/database")
      .option("dbtable", "products")
      .option("user", "user")
      .option("password", "password")
      .save()

    val postDumpDF = spark.read
      .format("jdbc")
      .option("driver", "com.mysql.cj.jdbc.Driver")
      .option("url", s"jdbc:mysql://$mysqlHost:3306/database")
      .option("dbtable", "products")
      .option("user", "user")
      .option("password", "password")
      .load()

    println(s"Total records extracted($inputFile): ${postDumpDF.count}")
    println("Hello world!")
  }
}