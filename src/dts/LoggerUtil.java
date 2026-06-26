
	package dts;

import java.io.FileWriter;
import java.io.IOException;

public class LoggerUtil {

    public static synchronized void log(String message) {

        try {

            FileWriter writer =
                    new FileWriter(
                            "scheduler.log",
                            true);

            writer.write(message + "\n");

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}