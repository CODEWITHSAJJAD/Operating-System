package OS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CaloriesCalc extends JFrame {
    private JTextField ageField, heightField, weightField;
    private JButton calculateButton;
    private JLabel resultLabel;
    private Connection connection;

    public CaloriesCalc() {
        // Initialize database connection
        initDBConnection();

        // Create JFrame container
        setTitle("Calorie Calculator");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        // Add text fields and labels with improved layout
        add(new JLabel("Age:"), gbc);
        ageField = new JTextField(5);
        add(ageField, gbc);

        add(new JLabel("Height (in cm):"), gbc);
        heightField = new JTextField(5);
        add(heightField, gbc);

        add(new JLabel("Weight (in kg):"), gbc);
        weightField = new JTextField(5);
        add(weightField, gbc);

        // Add calculate button with icon
        calculateButton = new JButton("Calculate Calories", new ImageIcon("path/to/icon.png"));
        add(calculateButton, gbc);

        // Add result label
        resultLabel = new JLabel("Calories needed: ");
        add(resultLabel, gbc);

        // Add button action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int age = Integer.parseInt(ageField.getText());
                int height = Integer.parseInt(heightField.getText());
                int weight = Integer.parseInt(weightField.getText());
                double calories = calculateCalories(age, height, weight);
                resultLabel.setText("Calories needed: " + calories);
                saveData(age, height, weight, calories);
            }
        });

        // Set visibility
        setVisible(true);
    }

    private void initDBConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=OS;user=sa;password=YourPassword;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveData(int age, int height, int weight, double calories) {
        String sql = "INSERT INTO CaloriesData (Age, Height, Weight, Calories) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, age);
            pstmt.setInt(2, height);
            pstmt.setInt(3, weight);
            pstmt.setDouble(4, calories);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double calculateCalories(int age, int height, int weight) {
        // Simplified formula for demonstration purposes
        return 10 * weight + 6.25 * height - 5 * age + 5;
    }

    public static void main(String[] args) {
        new CaloriesCalc();
    }
}
