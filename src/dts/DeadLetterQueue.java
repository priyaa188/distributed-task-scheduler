package dts;

import java.util.ArrayList;
import java.util.List;

public class DeadLetterQueue {

    public static List<Job> failedJobs =
            new ArrayList<>(); 
    public static List<Job> getFailedJobs() {
        return failedJobs;
    }

    public static void add(Job job) {

        failedJobs.add(job);
    }
}