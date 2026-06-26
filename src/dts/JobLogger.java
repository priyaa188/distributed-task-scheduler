
	package dts;

	import java.util.ArrayList;

	public class JobLogger {

	    public static ArrayList<String> logs =
	            new ArrayList<>();

	    public static synchronized void log(
	            String message) {

	        logs.add(message);

	        System.out.println(
	                "[LOG] " + message);
	    }
	}
