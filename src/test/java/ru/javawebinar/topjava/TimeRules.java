package ru.javawebinar.topjava;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TimeRules {
    private static final Logger log = LoggerFactory.getLogger("log");

    private static final StringBuilder results = new StringBuilder();

    public static final Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = "method: \"" + description.getMethodName() + "\" execution time " + TimeUnit.NANOSECONDS.toMillis(nanos) + " ms";
            results.append(result).append('\n');
            log.info(result);
        }
    };

    public static final ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            results.setLength(0);
        }

        @Override
        protected void after() {
            log.info(results + "\n");
        }
    };
}
