package dts;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class Worker implements Runnable {

    private String workerName;
    private PriorityBlockingQueue<Job> queue;
    private Random random = new Random();

    public Worker(String workerName,
                  PriorityBlockingQueue<Job> queue) {

        this.workerName = workerName;
        this.queue = queue;
    }

    @Override
    public void run() {

        while (true) {

            try {

                while (SchedulerStatus.paused) {
                    Thread.sleep(500);
                }

                Job job = queue.take();

                if (job.isCancelled()) {

                    System.out.println(
                            workerName
                            + " skipped cancelled job -> "
                            + job.getJobId());

                    Logger.log(
                            workerName
                            + " skipped cancelled job "
                            + job.getJobId());

                    continue;
                }

                SchedulerStatus.runningJobs.incrementAndGet();

                SchedulerStatus.workerLoad.merge(
                        workerName,
                        1,
                        Integer::sum);

                job.setWorkerName(workerName);

                job.setStartTime(
                        System.currentTimeMillis());

                job.setStatus(
                        JobStatus.RUNNING);

                String startMessage =
                        workerName
                        + " started -> "
                        + job;

                System.out.println(startMessage);
                Logger.log(startMessage);

                // Timeout

                if (job.getExecutionTime()
                        > Config.TIMEOUT) {

                    job.setStatus(
                            JobStatus.FAILED);

                    DeadLetterQueue.add(job);

                    JobHistory.add(job);

                    SchedulerStatus.failedJobs
                            .incrementAndGet();

                    SchedulerStatus.runningJobs
                            .decrementAndGet();

                    String timeoutMessage =
                            workerName
                            + " timeout -> "
                            + job;

                    System.out.println(
                            timeoutMessage);

                    Logger.log(
                            timeoutMessage);

                    continue;
                }

                Thread.sleep(
                        job.getExecutionTime());

                boolean failed =
                        random.nextInt(100)
                        < Config.FAILURE_RATE;

                if (failed) {

                    if (job.getRetryCount()
                            < Config.MAX_RETRY) {

                        job.incrementRetry();

                        job.setStatus(
                                JobStatus.PENDING);

                        SchedulerStatus.runningJobs
                                .decrementAndGet();

                        String retryMessage =
                                workerName
                                + " retrying -> "
                                + job;

                        System.out.println(
                                retryMessage);

                        Logger.log(
                                retryMessage);

                        queue.put(job);

                    } else {

                        job.setStatus(
                                JobStatus.FAILED);

                        DeadLetterQueue.add(job);

                        JobHistory.add(job);

                        SchedulerStatus.failedJobs
                                .incrementAndGet();

                        SchedulerStatus.runningJobs
                                .decrementAndGet();

                        String failedMessage =
                                workerName
                                + " failed -> "
                                + job;

                        System.out.println(
                                failedMessage);

                        Logger.log(
                                failedMessage);
                    }

                } else {

                    job.setCompletionTime(
                            System.currentTimeMillis());

                    long waitingTime =
                            job.getStartTime()
                            - job.getArrivalTime();

                    long turnaroundTime =
                            job.getCompletionTime()
                            - job.getArrivalTime();

                    SchedulerStatus.totalWaitingTime
                            += waitingTime;

                    SchedulerStatus.totalTurnaroundTime
                            += turnaroundTime;

                    SchedulerStatus.workerBusyTime.merge(
                            workerName,
                            (long) job.getExecutionTime(),
                            Long::sum);

                    SchedulerStatus.jobTypeStats.merge(
                            job.getJobType().toString(),
                            1,
                            Integer::sum);

                    SchedulerStatus.priorityStats.merge(
                            job.getPriority().toString(),
                            1,
                            Integer::sum);

                    job.setStatus(
                            JobStatus.COMPLETED);

                    JobHistory.add(job);

                    SchedulerStatus.completedJobs
                            .incrementAndGet();

                    SchedulerStatus.workerStats.merge(
                            workerName,
                            1,
                            Integer::sum);

                    SchedulerStatus.runningJobs
                            .decrementAndGet();

                    String completedMessage =
                            workerName
                            + " completed -> "
                            + job;

                    System.out.println(
                            completedMessage);

                    Logger.log(
                            completedMessage);
                }

            } catch (Exception e) {

                e.printStackTrace();

                Logger.log(
                        e.getMessage());
            }
        }
    }
}