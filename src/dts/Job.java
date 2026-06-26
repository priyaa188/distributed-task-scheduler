package dts;

public class Job implements Comparable<Job> {

    private int jobId;
    private String jobName;
    private JobType jobType;
    private Priority priority;
    private int executionTime;

    private JobStatus status;

    private int retryCount;

    private long arrivalTime;
    private long startTime;
    private long completionTime;

    private String workerName = "-";

    private boolean cancelled = false;

    public Job(int jobId,
               String jobName,
               JobType jobType,
               Priority priority,
               int executionTime) {

        this.jobId = jobId;
        this.jobName = jobName;
        this.jobType = jobType;
        this.priority = priority;
        this.executionTime = executionTime;

        this.status = JobStatus.PENDING;

        this.retryCount = 0;

        this.arrivalTime =
                System.currentTimeMillis();
    }

    public int getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public JobType getJobType() {
        return jobType;
    }

    public Priority getPriority() {
        return priority;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(
            JobStatus status) {

        this.status = status;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void incrementRetry() {
        retryCount++;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(
            long startTime) {

        this.startTime = startTime;
    }

    public long getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(
            long completionTime) {

        this.completionTime =
                completionTime;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(
            String workerName) {

        this.workerName =
                workerName;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(
            boolean cancelled) {

        this.cancelled =
                cancelled;
    }

    @Override
    public int compareTo(Job other) {

        if (this.priority.ordinal()
                < other.priority.ordinal()) {
            return 1;
        }

        if (this.priority.ordinal()
                > other.priority.ordinal()) {
            return -1;
        }

        return Long.compare(
                this.arrivalTime,
                other.arrivalTime);
    }

    @Override
    public String toString() {

        return "Job ID: "
                + jobId
                + ", Task: "
                + jobName
                + ", Type: "
                + jobType
                + ", Priority: "
                + priority
                + ", Time: "
                + executionTime
                + "ms, Status: "
                + status
                + ", Retries: "
                + retryCount;
    }
}