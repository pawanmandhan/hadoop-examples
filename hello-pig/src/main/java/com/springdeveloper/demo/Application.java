package com.springdeveloper.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.data.hadoop.pig.PigTemplate;

@ComponentScan
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

    private PigTemplate pigTemplate;

    private FsShell fsShell;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public void setPigTemplate(PigTemplate pigTemplate) {
        this.pigTemplate = pigTemplate;
    }

    @Autowired
    public void setFsShell(FsShell fsShell) {
        this.fsShell = fsShell;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("*** Hello Pig:");
        String script = "hashtags = LOAD '/test/data' USING PigStorage('\\t') AS (hashtag:chararray, count:int);" +
                "sorted = ORDER hashtags BY count DESC;" +
                "top10 = LIMIT sorted 10;" +
                "STORE top10 INTO '/test/results';";
        pigTemplate.executeScript(script);
        System.out.println("*** Results:");
        System.out.println(fsShell.cat("/test/results/*"));
    }

}
