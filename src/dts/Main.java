package dts;

import javax.swing.SwingUtilities;
import java.io.PrintWriter;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static void main(String[] args)
            throws Exception {

        SchedulerStatus.schedulerStartTime =
                System.currentTimeMillis();

        try {

            new PrintWriter(
                    "scheduler.log")
                    .close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        PriorityBlockingQueue<Job> queue =
                new PriorityBlockingQueue<>();

        SwingUtilities.invokeLater(() -> {

            new DashboardGUI();

            new JobInputGUI(queue);
        });

        for (int i = 1;
             i <= Config.WORKERS;
             i++) {

            Thread worker =
                    new Thread(
                            new Worker(
                                    "Worker-" + i,
                                    queue));

            worker.start();
        }

        queue.put(
                new Job(
                        1,
                        "Task-1",
                        JobType.IO,
                        Priority.LOW,
                        2000));

        queue.put(
                new Job(
                        2,
                        "Task-2",
                        JobType.NETWORK,
                        Priority.MEDIUM,
                        3000));

        queue.put(
                new Job(
                        3,
                        "Task-3",
                        JobType.DATABASE,
                        Priority.HIGH,
                        4000));

        queue.put(
                new Job(
                        4,
                        "Task-4",
                        JobType.CPU,
                        Priority.MEDIUM,
                        5000));

        queue.put(
                new Job(
                        5,
                        "Task-5",
                        JobType.IO,
                        Priority.LOW,
                        1000));

        queue.put(
                new Job(
                        6,
                        "Task-6",
                        JobType.NETWORK,
                        Priority.HIGH,
                        2000));

        queue.put(
                new Job(
                        7,
                        "Task-7",
                        JobType.DATABASE,
                        Priority.LOW,
                        3000));

        queue.put(
                new Job(
                        8,
                        "Task-8",
                        JobType.CPU,
                        Priority.MEDIUM,
                        4000));

        queue.put(
                new Job(
                        9,
                        "Task-9",
                        JobType.IO,
                        Priority.HIGH,
                        5000));

        queue.put(
                new Job(
                        10,
                        "Task-10",
                        JobType.NETWORK,
                        Priority.MEDIUM,
                        1000));

        Thread.sleep(30000);

        SchedulerStatus.schedulerEndTime =
                System.currentTimeMillis();

        ReportGenerator.generateReport();

        System.out.println(
                "\nReport generated successfully.");
    }
}