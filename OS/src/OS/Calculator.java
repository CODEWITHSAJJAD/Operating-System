package OS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator {

    private JFrame frame;
    private JTextField textField;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    private boolean isOperatorClicked = false;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Calculator window = new Calculator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public Calculator() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Create and add a text field at the top
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(false);
        frame.getContentPane().add(textField, BorderLayout.NORTH);

        // Create and add a panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // Button labels
        String[] buttons = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "0", "CL", "ANS", "/"
        };

        // Add buttons to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(button);

            // Add action listener to the button
            button.addActionListener(new ButtonClickListener());
        }
    }

    /**
     * Handle button clicks.
     */
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            try {
                if ("0123456789".contains(command)) {
                    // Append digits to the current input
                    if (isOperatorClicked) {
                        textField.setText(""); // Clear the field if an operator was just clicked
                        isOperatorClicked = false;
                    }
                    textField.setText(textField.getText() + command);
                } else if ("+-*/".contains(command)) {
                    // Store the first number and operator
                    if (!textField.getText().isEmpty()) {
                        num1 = Double.parseDouble(textField.getText());
                        operator = command.charAt(0);
                        isOperatorClicked = true;
                    }
                } else if ("ANS".equals(command)) {
                    // Perform the calculation
                    if (!textField.getText().isEmpty()) {
                        num2 = Double.parseDouble(textField.getText());
                        switch (operator) {
                            case '+': result = num1 + num2; break;
                            case '-': result = num1 - num2; break;
                            case '*': result = num1 * num2; break;
                            case '/':
                                if (num2 == 0) {
                                    textField.setText("Error: Division by 0");
                                    return;
                                }
                                result = num1 / num2;
                                break;
                            default: return;
                        }
                        textField.setText(String.valueOf(result));
                        num1 = result; // Save the result for further operations
                    }
                } else if ("CL".equals(command)) {
                    // Clear all fields
                    textField.setText("");
                    num1 = 0;
                    num2 = 0;
                    result = 0;
                    operator = '\0';
                }
            } catch (Exception ex) {
                textField.setText("Error");
            }
        }
    }
}