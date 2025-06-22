import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class fcsc {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextArea resultarea;
    private final String url = "jdbc:sqlserver://SUQOON\\SUQOON;databaseName=OS;user=sa;password=123456;encrypt=false;";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    fcsc window = new fcsc();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public fcsc() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout springLayout = new SpringLayout();
        frame.getContentPane().setLayout(springLayout);

        JLabel lblNewLabel = new JLabel("PID");
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 46, SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 26, SpringLayout.WEST, frame.getContentPane());
        frame.getContentPane().add(lblNewLabel);

        JLabel lblProcessName = new JLabel("Process Name");
        springLayout.putConstraint(SpringLayout.WEST, lblProcessName, 0, SpringLayout.WEST, lblNewLabel);
        frame.getContentPane().add(lblProcessName);

        JLabel lblArrivalTime = new JLabel("Arrival Time");
        springLayout.putConstraint(SpringLayout.NORTH, lblArrivalTime, 127, SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblArrivalTime, 0, SpringLayout.WEST, lblNewLabel);
        frame.getContentPane().add(lblArrivalTime);

        JLabel lblBrustTime = new JLabel("Burst Time");
        springLayout.putConstraint(SpringLayout.NORTH, lblBrustTime, 21, SpringLayout.SOUTH, lblArrivalTime);
        springLayout.putConstraint(SpringLayout.WEST, lblBrustTime, 0, SpringLayout.WEST, lblNewLabel);
        frame.getContentPane().add(lblBrustTime);

        JLabel lblPriority = new JLabel("Priority");
        springLayout.putConstraint(SpringLayout.NORTH, lblPriority, 23, SpringLayout.SOUTH, lblBrustTime);
        springLayout.putConstraint(SpringLayout.WEST, lblPriority, 0, SpringLayout.WEST, lblNewLabel);
        frame.getContentPane().add(lblPriority);

        textField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, lblNewLabel);
        springLayout.putConstraint(SpringLayout.WEST, textField, 17, SpringLayout.EAST, lblNewLabel);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, textField_1, 0, SpringLayout.NORTH, lblProcessName);
        springLayout.putConstraint(SpringLayout.WEST, textField_1, 3, SpringLayout.EAST, lblProcessName);
        textField_1.setColumns(10);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, textField_2, -3, SpringLayout.NORTH, lblArrivalTime);
        springLayout.putConstraint(SpringLayout.WEST, textField_2, 6, SpringLayout.EAST, lblArrivalTime);
        textField_2.setColumns(10);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, textField_3, -3, SpringLayout.NORTH, lblBrustTime);
        springLayout.putConstraint(SpringLayout.WEST, textField_3, 1, SpringLayout.EAST, lblBrustTime);
        textField_3.setColumns(10);
        frame.getContentPane().add(textField_3);

        textField_4 = new JTextField();
        springLayout.putConstraint(SpringLayout.SOUTH, textField_4, 0, SpringLayout.SOUTH, lblPriority);
        springLayout.putConstraint(SpringLayout.EAST, textField_4, 0, SpringLayout.EAST, textField);
        textField_4.setColumns(10);
        frame.getContentPane().add(textField_4);

        JButton btnSave = new JButton("Save");
        springLayout.putConstraint(SpringLayout.WEST, btnSave, 64, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, btnSave, -5, SpringLayout.SOUTH, frame.getContentPane());
        frame.getContentPane().add(btnSave);

        JButton btnLoad = new JButton("Load");
        springLayout.putConstraint(SpringLayout.WEST, btnLoad, 41, SpringLayout.EAST, btnSave);
        springLayout.putConstraint(SpringLayout.SOUTH, btnLoad, 0, SpringLayout.SOUTH, btnSave);
        frame.getContentPane().add(btnLoad);

        // Add the result area to display query results
        resultarea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(resultarea);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, textField_4);
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -50, SpringLayout.NORTH, btnSave);
        frame.getContentPane().add(scrollPane);
        resultarea.setEditable(false);

        // Save Button Action
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pid = textField.getText();
                String processName = textField_1.getText();
                String arrivalTime = textField_2.getText();
                String burstTime = textField_3.getText();
                String priority = textField_4.getText();

                try (Connection conn = DriverManager.getConnection(url)) {
                    String sql = "INSERT INTO FCSC (PID,[Process Name],AT,BT,Priority) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, pid);
                    pstmt.setString(2, processName);
                    pstmt.setString(3, arrivalTime);
                    pstmt.setString(4, burstTime);
                    pstmt.setString(5, priority);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Process details saved to database.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Clear text fields
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textField_3.setText("");
                textField_4.setText("");
            }
        });

        // Load Button Action
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder resultText = new StringBuilder();
                
                try (Connection conn = DriverManager.getConnection(url)) {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM FCSC");

                    resultText.append("Loaded Process Details from Database:\n");
                    while (rs.next()) {
                        resultText.append("PID: ").append(rs.getString("PID"))
                                  .append(", Process Name: ").append(rs.getString("Process Name"))
                                  .append(", Arrival Time: ").append(rs.getString("AT"))
                                  .append(", Burst Time: ").append(rs.getString("BT"))
                                  .append(", Priority: ").append(rs.getString("Priority"))
                                  .append("\n");
                    }

                    // Display the results in the JTextArea
                    resultarea.setText(resultText.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    resultarea.setText("Error loading data.");
                }
            }
        });
    }
}