package com.asb.parallelism.controller;

import com.asb.parallelism.worker.MyWorker;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

/**
 * Controller
 * Created by arjun on 09/04/17.
 */
@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController {

    private final ForkJoinPool forkJoinPool;
    private final Map<String, MyWorker> myWorkers;

    @Inject
    public SearchController(ForkJoinPool forkJoinPool, Map<String, MyWorker> myWorkers) {
        this.forkJoinPool = forkJoinPool;
        this.myWorkers = myWorkers;
    }

    @Data
    @Builder
    private static class Result {
        private Map<String, String> response;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Result search() throws Exception {

        long start = System.currentTimeMillis();
        Map<String, String> result = forkJoinPool
                .submit(() -> myWorkers.entrySet()
                        .stream()
                        .parallel()
                        .map(entry -> Maps.immutableEntry(entry.getKey(), entry.getValue()
                                .doMagix("Hello Worker", 2000)))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                       )
                .get();
        log.info("Time taken: {}", System.currentTimeMillis() - start);
        return Result.builder()
                .response(result)
                .build();
    }

}
