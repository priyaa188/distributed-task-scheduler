package dts;

import java.io.FileWriter;
import java.io.PrintWriter;

public class CSVExporter {

    public static void export() {

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(
                                    "jobs.csv"));

            writer.println(
                    "ID,Task,Priority,Type,Status,Retries");

            for (Job job : JobHistory.jobs) {

                writer.println(
                        job.getJobId()
                        + ","
                        + job.getJobName()
                        + ","
                        + job.getPriority()
                        + ","
                        + job.getJobType()
                        + ","
                        + job.getStatus()
                        + ","
                        + job.getRetryCount());
            }

            writer.close();

            System.out.println(
                    "CSV exported successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}