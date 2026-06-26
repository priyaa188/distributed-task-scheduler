# Distributed Task Scheduler

### Java Multithreaded Job Execution Engine with Real-Time GUI Dashboard

A Java-based multithreaded task scheduling system that executes prioritized jobs across concurrent worker threads. The project features real-time GUI monitoring, automatic retry logic, timeout handling, a Dead Letter Queue, and detailed performance statistics.

---

## Features

- Priority Scheduling using `PriorityBlockingQueue`
- Concurrent execution using 4 worker threads
- Automatic retry mechanism for failed jobs
- Timeout handling for long-running jobs
- Dead Letter Queue for permanently failed jobs
- Job cancellation support
- Real-time Swing dashboard
- Logging system using log files
- CSV report generation
- Performance monitoring and worker statistics

---

## Project Structure

```text
DistributedTaskScheduler/
├── src/
│   └── dts/
│       ├── Main.java
│       ├── Job.java
│       ├── Worker.java
│       ├── Config.java
│       ├── SchedulerStatus.java
│       ├── ReportGenerator.java
│       ├── JobHistory.java
│       ├── DeadLetterQueue.java
│       ├── CSVExporter.java
│       ├── Logger.java
│       ├── LoggerUtil.java
│       ├── JobLogger.java
│       ├── CompletedJobs.java
│       ├── PerformanceTracker.java
│       ├── WorkerStats.java
│       ├── DashboardGUI.java
│       ├── JobInputGUI.java
│       ├── JobSubmissionGUI.java
│       ├── Dashboard.java
│       ├── Priority.java
│       ├── JobType.java
│       └── JobStatus.java
├── jobs.csv
├── report.csv
├── report.txt
├── scheduler.log
└── README.md
```

---

## How It Works

```text
User submits a job
        │
        ▼
PriorityBlockingQueue
        │
        ▼
┌───────────────────────┐
│ Worker-1  Worker-2    │
│ Worker-3  Worker-4    │
└───────────────────────┘
        │
   ┌────┴────┐
   ▼         ▼
Completed  Failed
              │
         Retry (2 times)
              │
         Still failed?
              │
              ▼
     Dead Letter Queue
```

---

## Technologies Used

| Category | Technology |
|---------|------------|
| Language | Java 17 |
| Concurrency | PriorityBlockingQueue, ExecutorService |
| GUI | Java Swing |
| Logging | Custom Logger |
| Data Export | CSV |
| IDE | Eclipse |

---

## Getting Started

### Prerequisites

- Java 17
- Eclipse IDE

### Running the Project

1. Open Eclipse.
2. Import the project.
3. Navigate to `Main.java`.
4. Run the application.
5. Use the Job Input GUI to submit jobs.

---

## Sample Output

```text
[Worker-1] Executing: DataBackup
[Worker-2] Executing: APICall

[Worker-1] Completed: DataBackup
[Worker-3] Failed: ReportGen

Retrying job...

Completed Jobs : 7
Failed Jobs    : 3
Throughput     : 2.3 jobs/min
```

---

## Concepts Demonstrated

- Java Multithreading
- Concurrent Programming
- Priority Scheduling
- Thread Synchronization
- Object-Oriented Programming
- Exception Handling
- File Handling
- GUI Development
- Performance Analysis

---

## Future Enhancements

- MySQL integration
- Spring Boot REST API
- Web dashboard
- Distributed worker nodes
- Docker deployment
- Performance graphs

---

## Skills Demonstrated

- Java
- Multithreading
- Collections Framework
- Swing GUI
- File Handling
- Logging Systems
- Concurrent Programming
- OOP Design

---

## Author

**Priyadharshini KR**  
B.Tech Information Technology  
Velammal College of Engineering & Technology, Madurai

GitHub: https://github.com/priyaa188

---

## License

This project is developed for educational and learning purposes.
