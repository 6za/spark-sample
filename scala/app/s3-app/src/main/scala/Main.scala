import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

object Main {
  def main(args: Array[String]): Unit = {

    val cassandraHost = sys.env.getOrElse("CASSANDRA_HOST", "db-cassandra")
    val s3InFolder = sys.env.getOrElse("IN_FOLDER", "in")
    val s3OutFolder = sys.env.getOrElse("OUT_FOLDER", "out")
    val s3User = sys.env.getOrElse("S3_ACCESS_KEY", "user")
    val s3Pwd = sys.env.getOrElse("S3_SECRET_KEY", "password")
    val s3BucketIn = sys.env.getOrElse("S3_BUCKET_IN", "bucketin")
    val s3Endpoint = sys.env.getOrElse("S3_ENDPOINT", "http://s3-host:9000")

    val spark: SparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("S3App")
      .getOrCreate()

    // Replace Key with your AWS account key (You can find this on IAM
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.access.key", s3User)

    // Replace Key with your AWS secret key (You can find this on IAM

    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.secret.key", s3Pwd)

    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.endpoint", s3Endpoint)

    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.connection.ssl.enabled", "false")

    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.path.style.access", "true")

    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
    spark.sparkContext
      .hadoopConfiguration.set("com.amazonaws.services.s3.enableV2", "true")
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.aws.credentials.provider", "org.apache.hadoop.fs.s3a.SimpleAWSCredentialsProvider")

    val locationFiles = f"s3a://$s3BucketIn/products.csv"
    val sourceDF = spark.read.format("csv").option("header", "true").load(locationFiles)

    println(s"Total records extracted($locationFiles): ${sourceDF.count}")
  }
}