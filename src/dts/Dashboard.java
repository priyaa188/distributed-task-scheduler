package dts;

import java.util.concurrent.PriorityBlockingQueue;

public class Dashboard implements Runnable {

    private PriorityBlockingQueue<Job> queue;

    public Dashboard(
            PriorityBlockingQueue<Job> queue) {

        this.queue = queue;
    }

    @Override
    public void run() {

        while (true) {

            try {

            	if (queue.size() == 0 &&
            		    SchedulerStatus.completedJobs.get() == 0 &&
            		    SchedulerStatus.failedJobs.get() == 0) {

            		    Thread.sleep(1000);
            		    continue;
            		}

            		System.out.println(
            		        "\n===== DASHBOARD =====");
            		
            		
                System.out.println(
                        "Completed Jobs: "
                        + SchedulerStatus.completedJobs.get());

                System.out.println(
                        "Failed Jobs: "
                        + SchedulerStatus.failedJobs.get());

                System.out.println(
                        "Jobs Remaining: "
                        + queue.size());

                int finished =
                        SchedulerStatus.completedJobs.get()
                        + SchedulerStatus.failedJobs.get();

                if (finished > 0) {

                    System.out.println(
                            "Average Waiting Time: "
                            + (SchedulerStatus.totalWaitingTime
                            / finished)
                            + " ms");
                }

                System.out.println(
                        "=====================");

                Thread.sleep(5000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}