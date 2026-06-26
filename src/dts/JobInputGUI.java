package dts;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.PriorityBlockingQueue;

public class JobInputGUI extends JFrame {

    private static int jobId = 11;

    public JobInputGUI(
            PriorityBlockingQueue<Job> queue) {

        setTitle("Add New Job");

        setSize(350,300);

        setLayout(new GridLayout(5,2));

        JLabel nameLabel =
                new JLabel("Task Name:");

        JTextField nameField =
                new JTextField();

        JLabel timeLabel =
                new JLabel("Execution Time:");

        JTextField timeField =
                new JTextField();

        JLabel priorityLabel =
                new JLabel("Priority:");

        JComboBox<Priority> priorityBox =
                new JComboBox<>(Priority.values());

        JLabel typeLabel =
                new JLabel("Job Type:");

        JComboBox<JobType> typeBox =
                new JComboBox<>(JobType.values());

        JButton addButton =
                new JButton("Add Job");

        add(nameLabel);
        add(nameField);

        add(timeLabel);
        add(timeField);

        add(priorityLabel);
        add(priorityBox);

        add(typeLabel);
        add(typeBox);

        add(new JLabel());
        add(addButton);

        addButton.addActionListener(e -> {

            try {

                String task =
                        nameField.getText();

                int time =
                        Integer.parseInt(
                                timeField.getText());

                Priority priority =
                        (Priority)
                                priorityBox.getSelectedItem();

                JobType type =
                        (JobType)
                                typeBox.getSelectedItem();

                Job job =
                        new Job(
                                jobId++,
                                task,
                                type,
                                priority,
                                time);

                queue.put(job);

                JOptionPane.showMessageDialog(
                        this,
                        "Job Added Successfully!");

                nameField.setText("");
                timeField.setText("");

            }
            catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input");
            }
        });

        setLocationRelativeTo(null);

        setVisible(true);
    }
}