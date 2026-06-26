package dts;

import java.time.LocalDateTime;

public class ReportGenerator {

    public static void generateReport() {

        System.out.println(
                "\n===== FINAL REPORT =====");

        System.out.println(
                "Generated At: "
                + LocalDateTime.now());

        System.out.println(
                "\nCompleted Jobs: "
                + SchedulerStatus.completedJobs.get());

        System.out.println(
                "Failed Jobs: "
                + SchedulerStatus.failedJobs.get());

        int total =
                SchedulerStatus.completedJobs.get()
                +
                SchedulerStatus.failedJobs.get();

        if (total > 0) {

            System.out.println(
                    "\nAverage Waiting Time: "
                    + SchedulerStatus.totalWaitingTime
                    / total
                    + " ms");

            System.out.println(
                    "Average Turnaround Time: "
                    + SchedulerStatus.totalTurnaroundTime
                    / total
                    + " ms");
        }

        long totalTime =
                SchedulerStatus.schedulerEndTime
                -
                SchedulerStatus.schedulerStartTime;

        double throughput =
                (double) total
                /
                (totalTime / 1000.0);

        System.out.println(
                "\n===== THROUGHPUT =====");

        System.out.printf(
                "Throughput: %.2f jobs/sec%n",
                throughput);

        System.out.println(
                "\n===== WORKER STATISTICS =====");

        SchedulerStatus.workerStats.forEach(
                (worker, jobs) ->
                        System.out.println(
                                worker
                                + " completed "
                                + jobs
                                + " jobs"));

        System.out.println(
                "\n===== WORKER LOAD =====");

        SchedulerStatus.workerLoad.forEach(
                (worker, jobs) ->
                        System.out.println(
                                worker
                                + " handled "
                                + jobs
                                + " jobs"));

        System.out.println(
                "\n===== JOB TYPE STATS =====");

        SchedulerStatus.jobTypeStats.forEach(
                (type, count) ->
                        System.out.println(
                                type
                                + " : "
                                + count));

        System.out.println(
                "\n===== PRIORITY STATS =====");

        SchedulerStatus.priorityStats.forEach(
                (priority, count) ->
                        System.out.println(
                                priority
                                + " : "
                                + count));

        System.out.println(
                "\n===== FAILED JOBS =====");

        for (Job job :
                DeadLetterQueue.getFailedJobs()) {

            System.out.println(job);
        }

        System.out.println(
                "\n===== WORKER UTILIZATION =====");

        SchedulerStatus.workerBusyTime.forEach(
                (worker, busyTime) -> {

                    double utilization =
                            (double) busyTime
                            * 100
                            / totalTime;

                    System.out.printf(
                            "%s : %.2f%%%n",
                            worker,
                            utilization);
                });

        CSVExporter.export();
    }
}