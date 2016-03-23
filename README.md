# hadoop-examples
Spring and Hadoop example code

### Installing Spark example on CDH

```
wget https://repository.cloudera.com/artifactory/libs-release-local/org/apache/spark/spark-assembly_2.10/1.5.0-cdh5.5.0/spark-assembly_2.10-1.5.0-cdh5.5.0.jar
wget https://github.com/trisberg/hadoop-examples/blob/master/spark-yarn/app/spark-hashtags_2.10-0.1.0.jar
HADOOP_USER_NAME=hdfs hadoop fs -mkdir -p /app/spark
HADOOP_USER_NAME=hdfs hadoop fs -copyFromLocal spark-assembly_2.10-1.5.0-cdh5.5.0.jar /app/spark/spark-assembly_2.10-1.5.0-cdh5.5.0.jar
HADOOP_USER_NAME=hdfs hadoop fs -copyFromLocal spark-hashtags_2.10-0.1.0.jar /app/spark/spark-hashtags_2.10-0.1.0.jar
```
