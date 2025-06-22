import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class studentJ_table {
	String n1=null,n2=null,n3=null,n4=null,n5=null;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	private JTable table_1;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentJ_table window = new studentJ_table();
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
	public studentJ_table() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 564, 473);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblPid = new JLabel("PID");
		lblPid.setFont(new Font("Tahoma", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblPid, 20, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblPid, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblPid, 50, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblPid);
		
		JLabel lblArrivalTme = new JLabel("Arrival Tme");
		springLayout.putConstraint(SpringLayout.WEST, lblArrivalTme, 0, SpringLayout.WEST, lblPid);
		lblArrivalTme.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblArrivalTme);
		
		JLabel lblProcessName = new JLabel("Process Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblArrivalTme, 17, SpringLayout.SOUTH, lblProcessName);
		springLayout.putConstraint(SpringLayout.NORTH, lblProcessName, 23, SpringLayout.SOUTH, lblPid);
		springLayout.putConstraint(SpringLayout.WEST, lblProcessName, 10, SpringLayout.WEST, frame.getContentPane());
		lblProcessName.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblProcessName);
		
		JLabel lblBurstTme = new JLabel("Burst Tme");
		springLayout.putConstraint(SpringLayout.NORTH, lblBurstTme, 23, SpringLayout.SOUTH, lblArrivalTme);
		springLayout.putConstraint(SpringLayout.WEST, lblBurstTme, 0, SpringLayout.WEST, lblPid);
		lblBurstTme.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblBurstTme);
		
		JLabel lblPriority = new JLabel("Priority");
		springLayout.putConstraint(SpringLayout.NORTH, lblPriority, 18, SpringLayout.SOUTH, lblBurstTme);
		springLayout.putConstraint(SpringLayout.WEST, lblPriority, 0, SpringLayout.WEST, lblPid);
		lblPriority.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblPriority);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 20, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 125, SpringLayout.EAST, lblPid);
		springLayout.putConstraint(SpringLayout.EAST, textField, 266, SpringLayout.EAST, lblPid);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 20, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField);
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, 14, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, textField_2, -141, SpringLayout.EAST, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, textField);
		textField_2.setColumns(10);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_3, 20, SpringLayout.SOUTH, textField_2);
		springLayout.putConstraint(SpringLayout.WEST, textField_3, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_3, 0, SpringLayout.EAST, textField);
		textField_3.setColumns(10);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_4, 15, SpringLayout.SOUTH, textField_3);
		springLayout.putConstraint(SpringLayout.WEST, textField_4, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_4, 0, SpringLayout.EAST, textField);
		textField_4.setColumns(10);
		frame.getContentPane().add(textField_4);
		
		table = new JTable();
		springLayout.putConstraint(SpringLayout.NORTH, table, 100, SpringLayout.SOUTH, textField_4);
		springLayout.putConstraint(SpringLayout.WEST, table, 228, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, 100, SpringLayout.SOUTH, textField_4);
		springLayout.putConstraint(SpringLayout.EAST, table, 306, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(table);
		
		table_1 = new JTable();
		springLayout.putConstraint(SpringLayout.NORTH, table_1, 13, SpringLayout.SOUTH, textField_4);
		springLayout.putConstraint(SpringLayout.WEST, table_1, -94, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table_1, 13, SpringLayout.SOUTH, textField_4);
		springLayout.putConstraint(SpringLayout.EAST, table_1, -94, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(table_1);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection url = null;
				PreparedStatement pst;
				Statement st;
				ResultSet rs;
				n1=textField.getText();
				n2=textField_1.getText();
				n3=textField_2.getText();
				n4=textField_3.getText();
				n5=textField_4.getText();
				try {
					url=DriverManager.getConnection("jdbc:sqlserver://SUQOON\\SUQOON;databaseName=OS;user=sa;password=123456;encrypt=false;");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String query1="insert into Processtable (Pid,Process_Name,Arrival_Time,Burst_Time,Priority) values (?,?,?,?,? )";
				try(PreparedStatement pst1=url.prepareStatement(query1)){
				pst1.setString(1,n1);
				pst1.setString(2,n2);
				pst1.setString(3,n3);
				pst1.setString(4,n4);
				pst1.setString(5,n5);
				pst1.executeUpdate();
				
				}
				catch(SQLException e){
					System.out.println("please enter valid information");
				
				}
			
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSave, -1, SpringLayout.NORTH, lblArrivalTme);
		springLayout.putConstraint(SpringLayout.WEST, btnSave, 51, SpringLayout.EAST, textField_2);
		frame.getContentPane().add(btnSave);
		
		JButton btnLoad = new JButton("load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_5.setText(String.valueOf(n1));
				textField_6.setText(String.valueOf(n2));
				textField_7.setText(String.valueOf(n3));
				textField_8.setText(String.valueOf(n4));
				textField_9.setText(String.valueOf(n5));
			
			
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnLoad, 242, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(btnLoad);
		
		textField_5 = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField_5, 0, SpringLayout.WEST, lblPid);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_5, 0, SpringLayout.SOUTH, table);
		textField_5.setColumns(10);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_6, 0, SpringLayout.NORTH, textField_5);
		springLayout.putConstraint(SpringLayout.WEST, textField_6, 24, SpringLayout.EAST, textField_5);
		textField_6.setColumns(10);
		frame.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, btnLoad, -19, SpringLayout.NORTH, textField_7);
		springLayout.putConstraint(SpringLayout.NORTH, textField_7, 0, SpringLayout.NORTH, textField_5);
		springLayout.putConstraint(SpringLayout.EAST, textField_7, 0, SpringLayout.EAST, textField);
		textField_7.setColumns(10);
		frame.getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, textField_8, 0, SpringLayout.SOUTH, table);
		springLayout.putConstraint(SpringLayout.EAST, textField_8, 0, SpringLayout.EAST, btnSave);
		textField_8.setColumns(10);
		frame.getContentPane().add(textField_8);
		
		textField_9 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_9, 0, SpringLayout.NORTH, textField_5);
		springLayout.putConstraint(SpringLayout.EAST, textField_9, -21, SpringLayout.EAST, frame.getContentPane());
		textField_9.setColumns(10);
		frame.getContentPane().add(textField_9);
	}
}