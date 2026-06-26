package dts;

import java.util.ArrayList;
import java.util.List;

public class CompletedJobs {

    public static List<Job> jobs =
            new ArrayList<>();

    public static void add(Job job) {
        jobs.add(job);
    }
}