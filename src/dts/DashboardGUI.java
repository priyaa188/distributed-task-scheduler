package dts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardGUI extends JFrame {

    private JLabel completedLabel;
    private JLabel failedLabel;
    private JLabel runningLabel;
    private JLabel uptimeLabel;

    private JProgressBar progressBar;

    private JTable table;
    private DefaultTableModel model;

    public DashboardGUI() {

        setTitle("Distributed Task Scheduler");

        setSize(950,550);

        setLayout(new BorderLayout());

        JPanel top =
                new JPanel(new GridLayout(3,2));

        completedLabel =
                new JLabel("Completed: 0");

        failedLabel =
                new JLabel("Failed: 0");

        runningLabel =
                new JLabel("Running: 0");

        uptimeLabel =
                new JLabel("Uptime: 0 sec");

        progressBar =
                new JProgressBar();

        progressBar.setMinimum(0);

        progressBar.setMaximum(10);

        top.add(completedLabel);
        top.add(failedLabel);
        top.add(runningLabel);
        top.add(uptimeLabel);
        top.add(progressBar);

        add(top, BorderLayout.NORTH);

        model =
                new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Task");
        model.addColumn("Status");
        model.addColumn("Priority");
        model.addColumn("Worker");

        table =
                new JTable(model);

        add(new JScrollPane(table),
                BorderLayout.CENTER);

        JPanel buttons =
                new JPanel();

        JButton pause =
                new JButton("Pause");

        JButton resume =
                new JButton("Resume");

        JButton cancel =
                new JButton("Cancel Job");

        JButton search =
                new JButton("Search");

        pause.addActionListener(
                e -> SchedulerStatus.paused = true);

        resume.addActionListener(
                e -> SchedulerStatus.paused = false);

        cancel.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if(row >= 0){

                int id =
                        Integer.parseInt(
                                model.getValueAt(
                                        row,
                                        0).toString());

                for(Job job : JobHistory.jobs){

                    if(job.getJobId() == id){

                        job.setCancelled(true);

                        JOptionPane.showMessageDialog(
                                this,
                                "Job Cancelled");

                        return;
                    }
                }
            }
        });

        search.addActionListener(e -> {

            String input =
                    JOptionPane.showInputDialog(
                            "Enter Job ID");

            try {

                int id =
                        Integer.parseInt(input);

                for(Job job :
                        JobHistory.jobs){

                    if(job.getJobId() == id){

                        JOptionPane.showMessageDialog(
                                this,
                                job.toString());

                        return;
                    }
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Job Not Found");

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid ID");
            }
        });

        buttons.add(pause);
        buttons.add(resume);
        buttons.add(cancel);
        buttons.add(search);

        add(buttons,
                BorderLayout.SOUTH);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setVisible(true);

        Timer timer =
                new Timer(
                        1000,
                        e -> updateDashboard());

        timer.start();
    }

    private void updateDashboard() {

        completedLabel.setText(
                "Completed: "
                + SchedulerStatus.completedJobs.get());

        failedLabel.setText(
                "Failed: "
                + SchedulerStatus.failedJobs.get());

        runningLabel.setText(
                "Running: "
                + SchedulerStatus.runningJobs.get());

        long uptime =
                (System.currentTimeMillis()
                - SchedulerStatus.schedulerStartTime)
                / 1000;

        uptimeLabel.setText(
                "Uptime: "
                + uptime + " sec");

        progressBar.setValue(
                SchedulerStatus.completedJobs.get()
                +
                SchedulerStatus.failedJobs.get());

        model.setRowCount(0);

        for(Job job : JobHistory.jobs){

            model.addRow(
                    new Object[]{
                            job.getJobId(),
                            job.getJobName(),
                            job.getStatus(),
                            job.getPriority(),
                            job.getWorkerName()
                    });
        }
    }
}