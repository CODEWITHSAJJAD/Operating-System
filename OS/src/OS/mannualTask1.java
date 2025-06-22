package OS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class mannualTask1 {

	private JFrame frame;
	private JTable table;
	private final String url = "jdbc:sqlserver://SUQOON\\SUQOON;databaseName=OS;user=sa;password=123456;encrypt=false;";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mannualTask1 window = new mannualTask1();
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
	public mannualTask1() {
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
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Process"},
			},
			new String[] {
				"P1"
			}
		));
		springLayout.putConstraint(SpringLayout.NORTH, table, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, 224, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, 424, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(table);
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(url)) {
					Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM FCFS1");
                    DefaultTableModel model=(DefaultTableModel) table.getModel();                    
                    model.setRowCount(0); 
                    String pid;
					
                    while(rs.next()) {
                    	pid=rs.getString(1);
                    	
                    	String row []= {pid};
                    	model.addRow(row);
                    	
                    }

					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 4, SpringLayout.SOUTH, table);
		frame.getContentPane().add(btnNewButton);
	}
	
}
