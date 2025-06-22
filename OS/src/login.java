

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login {

    private JFrame frame;
    private JTextField emailField;
    private JTextField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login window = new login();
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
    public login() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 640, 402);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout springLayout = new SpringLayout();
        frame.getContentPane().setLayout(springLayout);

        JLabel lblEmail = new JLabel("Email");
        springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 33, SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblEmail, 48, SpringLayout.WEST, frame.getContentPane());
        frame.getContentPane().add(lblEmail);

        JLabel lblPassword = new JLabel("Password");
        springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 34, SpringLayout.SOUTH, lblEmail);
        springLayout.putConstraint(SpringLayout.EAST, lblPassword, 0, SpringLayout.EAST, lblEmail);
        frame.getContentPane().add(lblPassword);

        emailField = new JTextField();
        springLayout.putConstraint(SpringLayout.WEST, emailField, 24, SpringLayout.EAST, lblEmail);
        springLayout.putConstraint(SpringLayout.SOUTH, emailField, 0, SpringLayout.SOUTH, lblEmail);
        frame.getContentPane().add(emailField);
        emailField.setColumns(10);

        passwordField = new JTextField();
        springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, emailField);
        springLayout.putConstraint(SpringLayout.SOUTH, passwordField, 0, SpringLayout.SOUTH, lblPassword);
        frame.getContentPane().add(passwordField);
        passwordField.setColumns(10);

        JButton btnLogin = new JButton("Login");
        springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 19, SpringLayout.SOUTH, lblPassword);
        springLayout.putConstraint(SpringLayout.WEST, btnLogin, 10, SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnLogin, 88, SpringLayout.WEST, frame.getContentPane());
        frame.getContentPane().add(btnLogin);

        JButton btnSignup = new JButton("Signup");
        springLayout.putConstraint(SpringLayout.NORTH, btnSignup, 0, SpringLayout.NORTH, btnLogin);
        springLayout.putConstraint(SpringLayout.WEST, btnSignup, 58, SpringLayout.EAST, btnLogin);
        frame.getContentPane().add(btnSignup);

        // Add ActionListener for the Login button
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = passwordField.getText();
                
                // Basic example login check (you can modify this to check against a database or specific logic)
                if(email.equals("user@example.com") && password.equals("password123")) {
                    openWelcomeWindow();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid email or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Add ActionListener for the Signup button
        btnSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Signup functionality is not implemented yet.");
            }
        });
    }

    /**
     * Opens a new window with a welcome message.
     */
    private void openWelcomeWindow() {
        JFrame welcomeFrame = new JFrame("Welcome");
        welcomeFrame.setBounds(150, 150, 300, 200);
        welcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeFrame.getContentPane().add(welcomeLabel);
        welcomeFrame.setVisible(true);
    }
}