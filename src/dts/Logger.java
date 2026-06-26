package dts;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    private static final String FILE_NAME =
            "scheduler.log";

    public static synchronized void log(String message) {

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(
                                    FILE_NAME,
                                    true));

            writer.println(message);

            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}