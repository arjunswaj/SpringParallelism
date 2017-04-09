package com.asb.parallelism.worker.impl;

import com.asb.parallelism.controller.helper.Sleeper;
import com.asb.parallelism.worker.MyWorker;
import lombok.extern.slf4j.Slf4j;

/**
 * Slow Worker.
 * Created by arjun on 09/04/17.
 */
@Slf4j
public class SlowWorker implements MyWorker {

    @Override
    public String doMagix(String query, long duration) {
        log.info("Thread: {}", Thread.currentThread()
                .getName());
        Sleeper.sleep(duration);
        return "Slow Magical " + query;
    }

}
