
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
public class NameEntry {
	 String  n1=null;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
NameEntry window = new NameEntry();
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
	public NameEntry() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 59, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 32, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -170, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, 160, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		int i=0;
		
		JButton btnEnter = new JButton("Enter");
		springLayout.putConstraint(SpringLayout.NORTH, btnEnter, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, btnEnter, 58, SpringLayout.WEST, frame.getContentPane());
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection url = null;
				PreparedStatement pst;
				Statement st;
				ResultSet rs;
				n1=textField.getText();
				try {
					url=DriverManager.getConnection("jdbc:sqlserver://SUQOON\\\\SUQOON;databaseName=OS;user=sa;password=123456;encrypt=false;");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String query1="insert into tlpinfoo (Name) values (? )";
				try(PreparedStatement pst1=url.prepareStatement(query1)){
				pst1.setString(1,n1);
				pst1.executeUpdate();
				}
				catch(SQLException e){
					System.out.println("please enter valid information");
				
				}
				
			
			}
		});
		btnEnter.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnEnter);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 0, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, -168, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textField_1, 0, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, -38, SpringLayout.EAST, frame.getContentPane());
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setText(String.valueOf(n1));
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnLoad, 3, SpringLayout.NORTH, btnEnter);
		springLayout.putConstraint(SpringLayout.EAST, btnLoad, -89, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnLoad);
	
}	
}
