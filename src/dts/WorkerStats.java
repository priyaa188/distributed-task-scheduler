package dts;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkerStats {

    public static ConcurrentHashMap<String,
            AtomicInteger> workerJobs =
            new ConcurrentHashMap<>();

    public static void increment(String workerName) {

        workerJobs.putIfAbsent(
                workerName,
                new AtomicInteger(0));

        workerJobs.get(workerName)
                .incrementAndGet();
    }
}