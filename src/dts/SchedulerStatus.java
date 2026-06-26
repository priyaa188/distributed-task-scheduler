package dts;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SchedulerStatus {

    public static AtomicInteger completedJobs =
            new AtomicInteger(0);

    public static AtomicInteger failedJobs =
            new AtomicInteger(0);

    public static AtomicInteger runningJobs =
            new AtomicInteger(0);

    public static long totalWaitingTime = 0;

    public static long totalTurnaroundTime = 0;

    public static long schedulerStartTime = 0;

    public static long schedulerEndTime = 0;

    public static boolean paused = false;

    public static ConcurrentHashMap<String,Integer>
            workerStats =
            new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String,Integer>
            workerLoad =
            new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String,Long>
            workerBusyTime =
            new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String,Integer>
            jobTypeStats =
            new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String,Integer>
            priorityStats =
            new ConcurrentHashMap<>();
}