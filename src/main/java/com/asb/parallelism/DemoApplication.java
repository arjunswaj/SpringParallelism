package com.asb.parallelism;

import com.asb.parallelism.worker.MyWorker;
import com.asb.parallelism.worker.impl.FastWorker;
import com.asb.parallelism.worker.impl.SlowerWorker;
import com.google.common.collect.ImmutableMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ForkJoinPoolFactoryBean;

import java.util.Map;

@SpringBootApplication
public class DemoApplication {


    @Bean(name = "forkJoinPool")
    public ForkJoinPoolFactoryBean forkJoinPoolFactoryBean() {
        ForkJoinPoolFactoryBean forkJoinPoolFactoryBean =
                new ForkJoinPoolFactoryBean();
        forkJoinPoolFactoryBean.setCommonPool(false);
        // No. of Requests * TPS * Latency (in s)
        forkJoinPoolFactoryBean.setParallelism(3 * 2 * 2);
        return forkJoinPoolFactoryBean;
    }

    @Bean(name = "myWorkers")
    public Map<String, MyWorker> myWorkers() {
        return ImmutableMap.<String, MyWorker>builder()
                .put("Fast Worker", new FastWorker())
                .put("Slow Worker", new SlowerWorker())
                .put("Slower Worker", new SlowerWorker())
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
