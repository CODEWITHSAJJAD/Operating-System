

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Student_Details {

    private JFrame frame;
    private JTextField textFieldName;
    private JTextField textFieldAridNo;
    private JTextField textFieldSection;
    String url = "jdbc:sqlserver://SUQOON\\SUQOON;databaseName=OS;user=sa;password=123456;encrypt=false;";
    String username = "sa";
    String password = "123456";
    Connection connection = null;	
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Student_Details window = new Student_Details();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Student_Details() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout springLayout = new SpringLayout();
        frame.getContentPane().setLayout(springLayout);
        
        JLabel lblName = new JLabel("Name");
        springLayout.putConstraint(SpringLayout.NORTH, lblName, 42, SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblName, 21, SpringLayout.WEST, frame.getContentPane());
        frame.getContentPane().add(lblName);
        
        textFieldName = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, textFieldName, 0, SpringLayout.NORTH, lblName);
        springLayout.putConstraint(SpringLayout.WEST, textFieldName, 33, SpringLayout.EAST, lblName);
        frame.getContentPane().add(textFieldName);
        textFieldName.setColumns(10);
        
        JLabel lblAridNo = new JLabel("Arid No");
        springLayout.putConstraint(SpringLayout.NORTH, lblAridNo, 43, SpringLayout.SOUTH, lblName);
        springLayout.putConstraint(SpringLayout.WEST, lblAridNo, 0, SpringLayout.WEST, lblName);
        frame.getContentPane().add(lblAridNo);
        
        textFieldAridNo = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, textFieldAridNo, 0, SpringLayout.NORTH, lblAridNo);
        springLayout.putConstraint(SpringLayout.EAST, textFieldAridNo, 0, SpringLayout.EAST, textFieldName);
        frame.getContentPane().add(textFieldAridNo);
        textFieldAridNo.setColumns(10);
        
        JLabel lblSection = new JLabel("Section");
        springLayout.putConstraint(SpringLayout.NORTH, lblSection, 50, SpringLayout.SOUTH, lblAridNo);
        springLayout.putConstraint(SpringLayout.EAST, lblSection, 0, SpringLayout.EAST, lblAridNo);
        frame.getContentPane().add(lblSection);
        
        textFieldSection = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, textFieldSection, 0, SpringLayout.NORTH, lblSection);
        springLayout.putConstraint(SpringLayout.EAST, textFieldSection, 0, SpringLayout.EAST, textFieldName);
        frame.getContentPane().add(textFieldSection);
        textFieldSection.setColumns(10);
        
        JButton btnAdd = new JButton("Add");
        springLayout.putConstraint(SpringLayout.WEST, btnAdd, 24, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, btnAdd, -26, SpringLayout.SOUTH, frame.getContentPane());
        frame.getContentPane().add(btnAdd);
        
        JButton btnDisplay = new JButton("Display");
        springLayout.putConstraint(SpringLayout.NORTH, btnDisplay, 0, SpringLayout.NORTH, btnAdd);
        springLayout.putConstraint(SpringLayout.WEST, btnDisplay, 58, SpringLayout.EAST, btnAdd);
        frame.getContentPane().add(btnDisplay);

        // Add action listener for Add button
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        // Add action listener for Display button
        btnDisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });
    }

    // Method to add a student record to the database
    private void addStudent() {
        String name = textFieldName.getText();
        String aridNo = textFieldAridNo.getText();
        String section = textFieldSection.getText();

        String query = "INSERT INTO studentinfo (Name, AridNo, Section) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, aridNo); // Ensure this column name matches the database exactly
            pstmt.setString(3, section);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Student added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding student.");
        }
    }


    // Method to display all student records from the database
    private void displayStudents() {
        String query = "SELECT ID, Name, AridNo, Section FROM studentinfo"; // Ensure these column names match your table

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("ID\tName\tAridNo\tSection");
            while (rs.next()) {
                int id = rs.getInt("ID");          // Ensure these column names match your table
                String name = rs.getString("Name");
                String aridNo = rs.getString("AridNo");
                String section = rs.getString("Section");
                System.out.println(id + "\t" + name + "\t" + aridNo + "\t" + section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error displaying students.");
        }
    }
}
