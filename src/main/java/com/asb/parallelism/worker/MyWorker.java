package com.asb.parallelism.worker;

/**
 * My Worker.
 * Created by arjun on 09/04/17.
 */
@FunctionalInterface
public interface MyWorker {
    /**
     * Does the magix.
     *
     * @param query    some query
     * @param duration duration of the work
     * @return magix result
     */
    String doMagix(String query, long duration);
}
