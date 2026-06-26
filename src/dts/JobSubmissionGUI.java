package dts;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.PriorityBlockingQueue;

public class JobSubmissionGUI extends JFrame {

    private JTextField jobNameField;
    private JTextField executionField;

    private JComboBox<JobType> typeBox;
    private JComboBox<Priority> priorityBox;

    private PriorityBlockingQueue<Job> queue;

    private static int nextId = 11;

    public JobSubmissionGUI(
            PriorityBlockingQueue<Job> queue) {

        this.queue = queue;

        setTitle("Add New Job");

        setSize(350, 250);

        setLayout(new GridLayout(5, 2));

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("Job Name"));

        jobNameField =
                new JTextField();

        add(jobNameField);

        add(new JLabel("Execution Time"));

        executionField =
                new JTextField();

        add(executionField);

        add(new JLabel("Job Type"));

        typeBox =
                new JComboBox<>(
                        JobType.values());

        add(typeBox);

        add(new JLabel("Priority"));

        priorityBox =
                new JComboBox<>(
                        Priority.values());

        add(priorityBox);

        JButton button =
                new JButton(
                        "Submit Job");

        add(button);

        button.addActionListener(e -> {

            try {

                String name =
                        jobNameField.getText();

                int time =
                        Integer.parseInt(
                                executionField.getText());

                JobType type =
                        (JobType)
                                typeBox.getSelectedItem();

                Priority priority =
                        (Priority)
                                priorityBox.getSelectedItem();

                Job job =
                        new Job(
                                nextId++,
                                name,
                                type,
                                priority,
                                time);

                queue.put(job);

                JOptionPane.showMessageDialog(
                        this,
                        "Job Added Successfully");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input");
            }
        });

        setVisible(true);
    }
}