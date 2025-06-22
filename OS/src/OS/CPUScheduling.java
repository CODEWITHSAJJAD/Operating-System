package OS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

class Process {
    int id, arrivalTime, burstTime, remainingTime, completionTime, waitingTime, turnaroundTime;

    Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class CPUScheduling extends JFrame {

    private JTextField arrivalField, burstField, quantumField;
    private DefaultTableModel tableModel;
    private java.util.List<Process> processList = new java.util.ArrayList<>();

    public CPUScheduling() {
        setTitle("Round Robin Scheduling");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout setup
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Process"));

        inputPanel.add(new JLabel("Arrival Time:"));
        arrivalField = new JTextField();
        inputPanel.add(arrivalField);

        inputPanel.add(new JLabel("Burst Time:"));
        burstField = new JTextField();
        inputPanel.add(burstField);

        inputPanel.add(new JLabel("Time Quantum:"));
        quantumField = new JTextField();
        inputPanel.add(quantumField);

        JButton addButton = new JButton("Add Process");
        inputPanel.add(addButton);

        JButton computeButton = new JButton("Compute Round Robin");
        inputPanel.add(computeButton);

        add(inputPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"Process ID", "Arrival Time", "Burst Time", "Completion Time", "Waiting Time", "Turnaround Time"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Button actions
        addButton.addActionListener(e -> addProcess());
        computeButton.addActionListener(e -> computeRoundRobin());
    }

    private void addProcess() {
        try {
            String arrivalText = arrivalField.getText().trim();
            String burstText = burstField.getText().trim();

            if (arrivalText.isEmpty() || burstText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Both fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int arrivalTime = Integer.parseInt(arrivalText);
            int burstTime = Integer.parseInt(burstText);

            int processId = processList.size() + 1;
            Process newProcess = new Process(processId, arrivalTime, burstTime);
            processList.add(newProcess);
            tableModel.addRow(new Object[]{processId, arrivalTime, burstTime, "-", "-", "-"});

            arrivalField.setText("");
            burstField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter integers for Arrival and Burst Time.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void computeRoundRobin() {
        try {
            if (quantumField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter the time quantum!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int quantum = Integer.parseInt(quantumField.getText().trim());

            if (processList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No processes to compute. Add processes first.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Queue<Process> queue = new LinkedList<>();
            java.util.List<Process> remainingProcesses = new java.util.ArrayList<>(processList);

            int time = 0;
            while (!remainingProcesses.isEmpty() || !queue.isEmpty()) {
                while (!remainingProcesses.isEmpty() && remainingProcesses.get(0).arrivalTime <= time) {
                    queue.add(remainingProcesses.remove(0));
                }

                if (!queue.isEmpty()) {
                    Process current = queue.poll();
                    int executionTime = Math.min(current.remainingTime, quantum);
                    time += executionTime;
                    current.remainingTime -= executionTime;

                    if (current.remainingTime == 0) {
                        current.completionTime = time;
                        current.turnaroundTime = current.completionTime - current.arrivalTime;
                        current.waitingTime = current.turnaroundTime - current.burstTime;

                        tableModel.setValueAt(current.completionTime, current.id - 1, 3);
                        tableModel.setValueAt(current.waitingTime, current.id - 1, 4);
                        tableModel.setValueAt(current.turnaroundTime, current.id - 1, 5);
                    } else {
                        queue.add(current);
                    }
                } else {
                    time++;
                }
            }

            JOptionPane.showMessageDialog(this, "Round Robin computation completed!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter an integer for the Time Quantum.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CPUScheduling frame = new CPUScheduling();
            frame.setVisible(true);
        });
    }
}