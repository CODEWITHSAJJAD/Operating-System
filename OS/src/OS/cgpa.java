package OS;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cgpa {

    private JFrame frame;
    private JTextField sem1Field;
    private JTextField sem2Field;
    private JTextField sem3Field;
    private JTextField sem4Field;
    private JTextField sem5Field;
    private JLabel resultLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    cgpa window = new cgpa();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public cgpa() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("CGPA Calculator");
        lblTitle.setBounds(160, 10, 120, 20);
        frame.getContentPane().add(lblTitle);

        JLabel lblSem1 = new JLabel("Semester 1 GPA:");
        lblSem1.setBounds(50, 50, 120, 25);
        frame.getContentPane().add(lblSem1);

        sem1Field = new JTextField();
        sem1Field.setBounds(200, 50, 150, 25);
        frame.getContentPane().add(sem1Field);
        sem1Field.setColumns(10);

        JLabel lblSem2 = new JLabel("Semester 2 GPA:");
        lblSem2.setBounds(50, 90, 120, 25);
        frame.getContentPane().add(lblSem2);

        sem2Field = new JTextField();
        sem2Field.setBounds(200, 90, 150, 25);
        frame.getContentPane().add(sem2Field);
        sem2Field.setColumns(10);

        JLabel lblSem3 = new JLabel("Semester 3 GPA:");
        lblSem3.setBounds(50, 130, 120, 25);
        frame.getContentPane().add(lblSem3);

        sem3Field = new JTextField();
        sem3Field.setBounds(200, 130, 150, 25);
        frame.getContentPane().add(sem3Field);
        sem3Field.setColumns(10);

        JLabel lblSem4 = new JLabel("Semester 4 GPA:");
        lblSem4.setBounds(50, 170, 120, 25);
        frame.getContentPane().add(lblSem4);

        sem4Field = new JTextField();
        sem4Field.setBounds(200, 170, 150, 25);
        frame.getContentPane().add(sem4Field);
        sem4Field.setColumns(10);

        JLabel lblSem5 = new JLabel("Semester 5 GPA:");
        lblSem5.setBounds(50, 210, 120, 25);
        frame.getContentPane().add(lblSem5);

        sem5Field = new JTextField();
        sem5Field.setBounds(200, 210, 150, 25);
        frame.getContentPane().add(sem5Field);
        sem5Field.setColumns(10);

        JButton btnCalculate = new JButton("Calculate CGPA");
        btnCalculate.setBounds(45, 257, 150, 30);
        frame.getContentPane().add(btnCalculate);

        resultLabel = new JLabel("Your CGPA: ");
        resultLabel.setBounds(200, 260, 200, 25);
        frame.getContentPane().add(resultLabel);

        // Add Action Listener to Calculate Button
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateCGPA();
            }
        });
    }

    /**
     * Calculate CGPA based on input values.
     */
    private void calculateCGPA() {
        try {
            double sem1 = Double.parseDouble(sem1Field.getText());
            double sem2 = Double.parseDouble(sem2Field.getText());
            double sem3 = Double.parseDouble(sem3Field.getText());
            double sem4 = Double.parseDouble(sem4Field.getText());
            double sem5 = Double.parseDouble(sem5Field.getText());

            // Calculate CGPA
            double cgpa = (sem1 + sem2 + sem3 + sem4 + sem5) / 5;
            resultLabel.setText("Your CGPA: " + String.format("%.2f", cgpa));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid GPA values for all semesters.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}