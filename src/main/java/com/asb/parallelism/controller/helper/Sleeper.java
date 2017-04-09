package com.asb.parallelism.controller.helper;

import lombok.extern.slf4j.Slf4j;

/**
 * Util to sleep.
 * Created by arjun on 09/04/17.
 */
@Slf4j
public class Sleeper {

    public static void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            log.error("Interrupted Exception", e);
        }
    }

}
