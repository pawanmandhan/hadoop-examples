Simple Hadoop Pig App
=====================

Build with:

    mvn clean package

Copy test data:

    hadoop fs -mkdir -p /test/data/
    hadoop fs -copyFromLocal ./data/part-r-00000 /test/data/

Run using:

    java -jar target/hello-pig-0.1.0.jar

