package OS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class FCFS {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private final String url = "jdbc:sqlserver://SUQOON\\SUQOON;databaseName=OS;user=sa;password=123456;encrypt=false;";
	private JTextField textField_4;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FCFS window = new FCFS();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	public FCFS() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(0, 128, 128));
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.setBounds(100, 100, 538, 368);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("PID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 1, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -6, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField, 72, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.NORTH, frame.getContentPane());
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 17, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 74, SpringLayout.WEST, frame.getContentPane());
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1);
		
		JLabel lblPname = new JLabel("PNAME");
		springLayout.putConstraint(SpringLayout.NORTH, lblPname, 1, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, lblPname, -6, SpringLayout.WEST, textField_1);
		lblPname.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblPname);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, 55, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, textField_1);
		textField_2.setColumns(10);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_3, 18, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, textField_3, 0, SpringLayout.EAST, textField_1);
		textField_3.setColumns(10);
		frame.getContentPane().add(textField_3);
		
		JLabel lblArrivet = new JLabel("ARRIVE_T");
		springLayout.putConstraint(SpringLayout.WEST, lblArrivet, 9, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblArrivet, 0, SpringLayout.NORTH, textField_3);
		lblArrivet.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblArrivet);
		
		JLabel lblBurstt = new JLabel("BURST_T");
		springLayout.putConstraint(SpringLayout.NORTH, lblBurstt, 1, SpringLayout.NORTH, textField_2);
		springLayout.putConstraint(SpringLayout.EAST, lblBurstt, -6, SpringLayout.WEST, textField_2);
		lblBurstt.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblBurstt);
		
		JButton btnADD = new JButton("ADD");
		springLayout.putConstraint(SpringLayout.NORTH, btnADD, 7, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnADD, 28, SpringLayout.EAST, textField);
		frame.getContentPane().add(btnADD);
		

		
		JButton btnDisplay = new JButton("DISPLAY");
		springLayout.putConstraint(SpringLayout.EAST, btnADD, 73, SpringLayout.WEST, btnDisplay);
		springLayout.putConstraint(SpringLayout.WEST, btnDisplay, 26, SpringLayout.EAST, textField_1);
		springLayout.putConstraint(SpringLayout.NORTH, btnDisplay, 0, SpringLayout.NORTH, textField_1);
		frame.getContentPane().add(btnDisplay);
		
		JButton btnFcfs = new JButton("FCFS");
		springLayout.putConstraint(SpringLayout.NORTH, btnFcfs, 15, SpringLayout.SOUTH, btnDisplay);
		springLayout.putConstraint(SpringLayout.WEST, btnFcfs, 0, SpringLayout.WEST, btnADD);
		springLayout.putConstraint(SpringLayout.EAST, btnFcfs, 0, SpringLayout.EAST, btnADD);
		frame.getContentPane().add(btnFcfs);
		String [] columnNames= { "Process,AT,BT,WT,TAT"};
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setModel(new DefaultTableModel(
			    new Object[][] {},
			    new String[] { "PID", "Process", "AT", "BT", "CT", "TAT","WT" }
			));
		springLayout.putConstraint(SpringLayout.NORTH, table, 3, SpringLayout.SOUTH, textField_2);
		springLayout.putConstraint(SpringLayout.WEST, table, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, 54, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, 368, SpringLayout.EAST, lblNewLabel);
		frame.getContentPane().add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.SOUTH, textField_2);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(scrollPane);
		
		JButton btnClear = new JButton("CLEAR");
		springLayout.putConstraint(SpringLayout.NORTH, btnClear, -1, SpringLayout.NORTH, textField_2);
		springLayout.putConstraint(SpringLayout.WEST, btnClear, 0, SpringLayout.WEST, btnADD);
		springLayout.putConstraint(SpringLayout.EAST, btnClear, 0, SpringLayout.EAST, btnADD);
		frame.getContentPane().add(btnClear);
		
		JButton btnSjfpre = new JButton("SJFPre");
		springLayout.putConstraint(SpringLayout.NORTH, btnSjfpre, -2, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, btnSjfpre, 28, SpringLayout.EAST, btnADD);
		springLayout.putConstraint(SpringLayout.EAST, btnSjfpre, -132, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnSjfpre);
		
		JButton ROUND_ROBIN = new JButton("ROUND ROBIN");
		springLayout.putConstraint(SpringLayout.NORTH, ROUND_ROBIN, -1, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, ROUND_ROBIN, 0, SpringLayout.WEST, btnSjfpre);
		frame.getContentPane().add(ROUND_ROBIN);
		
		JButton btnSjfnonpre = new JButton("SJFNonPrE");
		springLayout.putConstraint(SpringLayout.NORTH, btnSjfnonpre, -1, SpringLayout.NORTH, textField_3);
		springLayout.putConstraint(SpringLayout.WEST, btnSjfnonpre, 0, SpringLayout.WEST, btnSjfpre);
		springLayout.putConstraint(SpringLayout.EAST, btnSjfnonpre, 131, SpringLayout.EAST, btnFcfs);
		frame.getContentPane().add(btnSjfnonpre);
		
		JLabel lblPRIORITY_1 = new JLabel("QUANTUM");
		springLayout.putConstraint(SpringLayout.NORTH, lblPRIORITY_1, 1, SpringLayout.NORTH, textField_2);
		springLayout.putConstraint(SpringLayout.EAST, lblPRIORITY_1, 0, SpringLayout.EAST, btnSjfpre);
		lblPRIORITY_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblPRIORITY_1);
		
		textField_4 = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField_4, 411, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, textField_4, 0, SpringLayout.NORTH, textField_2);
		textField_4.setColumns(10);
		frame.getContentPane().add(textField_4);
		btnADD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pid = textField.getText();
                String processName = textField_1.getText();
                String arrivalTime = textField_2.getText();
                String burstTime = textField_3.getText();
                int bt=Integer.parseInt(burstTime);
                int at=Integer.parseInt(arrivalTime);

                try (Connection conn = DriverManager.getConnection(url)) {
                    String sql = "INSERT INTO FCFS1 (PID,[Process Name],AT,BT) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, pid);
                    pstmt.setString(2, processName);                    
                    pstmt.setInt(3, bt);
                    pstmt.setInt(4, at);
                    
                    pstmt.executeUpdate();
                   
                    JOptionPane.showMessageDialog(frame, "Process details saved to database.");
                }catch (SQLException ex) {
                    if (ex.getErrorCode() == 2627 || ex.getErrorCode() == 2601) {
                        JOptionPane.showMessageDialog(frame, "Error: Duplicate PID. Please enter a unique PID.", "Database Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                    }
                    ex.printStackTrace();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(url)) {
					Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM FCFS1");
                    DefaultTableModel model=(DefaultTableModel) table.getModel();                    
                    model.setRowCount(0); 
                    String pid,pname,at1,bt1;
					int bt,at;
                    while(rs.next()) {
                    	pid=rs.getString(1);
                    	pname=rs.getString(2);
                    	at=rs.getInt(3);
                    	bt=rs.getInt(4);
                    	at1=String.valueOf(at);
                    	bt1=String.valueOf(bt);
                    	String row []= {pid,pname,at1,bt1};
                    	model.addRow(row);
                    	
                    }

					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnFcfs.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        int rowCount = model.getRowCount();
		        if (rowCount == 0) {
		            JOptionPane.showMessageDialog(frame, "No processes available to calculate FCFS.");
		            return;
		        }
		        try {		            
		            int[] pid = new int[rowCount];
		            int[] ar = new int[rowCount]; 
		            int[] bt = new int[rowCount]; 
		            int[] ct = new int[rowCount]; 
		            int[] ta = new int[rowCount]; 
		            int[] wt = new int[rowCount]; 
		            for (int i = 0; i < rowCount; i++) {
		                pid[i] = i + 1; 
		                ar[i] = Integer.parseInt(model.getValueAt(i, 2).toString());
		                bt[i] = Integer.parseInt(model.getValueAt(i, 3).toString());
		            }
		            
		            int temp;
		            for (int i = 0; i < rowCount; i++) {
		                for (int j = i + 1; j < rowCount; j++) {
		                    if (ar[i] > ar[j]) {
		                        
		                        temp = ar[i];
		                        ar[i] = ar[j];
		                        ar[j] = temp;
		                        
		                        temp = bt[i];
		                        bt[i] = bt[j];
		                        bt[j] = temp;
		                        
		                        temp = pid[i];
		                        pid[i] = pid[j];
		                        pid[j] = temp;
		                    }
		                }
		            }
		           
		            ct[0] = bt[0] + ar[0];
		            for (int i = 1; i < rowCount; i++) {
		                ct[i] = Math.max(ct[i - 1], ar[i]) + bt[i];
		            }
		            
		            for (int i = 0; i < rowCount; i++) {
		                ta[i] = ct[i] - ar[i]; 
		                wt[i] = ta[i] - bt[i]; 
		            }
		           
		            for (int i = 0; i < rowCount; i++) {
		                int tableRow = pid[i] - 1; 
		                model.setValueAt(ct[i], tableRow, 4); 
		                model.setValueAt(ta[i], tableRow, 5); 
		                model.setValueAt(wt[i], tableRow, 6); 
		            }

		            JOptionPane.showMessageDialog(frame, "FCFS calculation applied successfully.");
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(frame, "Error in data format. Ensure all times are valid integers.");
		            ex.printStackTrace();
		        }
		    }
		});
		btnSjfnonpre.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        int rowCount = model.getRowCount();
		        if (rowCount == 0) {
		            JOptionPane.showMessageDialog(frame, "No processes available to calculate SJF Non-Preemptive.");
		            return;
		        }
		        int[] pid = new int[rowCount];
		        int[] ar = new int[rowCount];
		        int[] bt = new int[rowCount];
		        int[] ct = new int[rowCount];
		        int[] ta = new int[rowCount];
		        int[] wt = new int[rowCount];

		        for (int i = 0; i < rowCount; i++) {
		            pid[i] = Integer.parseInt(model.getValueAt(i, 0).toString());
		            ar[i] = Integer.parseInt(model.getValueAt(i, 2).toString());
		            bt[i] = Integer.parseInt(model.getValueAt(i, 3).toString());
		        }

		        
		        for (int i = 0; i < rowCount; i++) {
		            for (int j = i + 1; j < rowCount; j++) {
		                if (bt[i] > bt[j]) {
		                    int temp = bt[i];
		                    bt[i] = bt[j];
		                    bt[j] = temp;

		                    temp = ar[i];
		                    ar[i] = ar[j];
		                    ar[j] = temp;

		                    temp = pid[i];
		                    pid[i] = pid[j];
		                    pid[j] = temp;
		                }
		            }
		        }

		       
		        ct[0] = ar[0] + bt[0];
		        for (int i = 1; i < rowCount; i++) {
		            ct[i] = Math.max(ct[i - 1], ar[i]) + bt[i];
		        }

		        for (int i = 0; i < rowCount; i++) {
		            ta[i] = ct[i] - ar[i];
		            wt[i] = ta[i] - bt[i];
		        }

		        for (int i = 0; i < rowCount; i++) {
		            int tableRow = pid[i] - 1;
		            model.setValueAt(ct[i], tableRow, 4);
		            model.setValueAt(ta[i], tableRow, 5);
		            model.setValueAt(wt[i], tableRow, 6);
		        }

		        JOptionPane.showMessageDialog(frame, "SJF Non-Preemptive calculation applied successfully.");
		    }
		});
		btnSjfpre.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        int rowCount = model.getRowCount();
		        if (rowCount == 0) {
		            JOptionPane.showMessageDialog(frame, "No processes available to calculate SJF Preemptive.");
		            return;
		        }

		        
		        int[][] processes = new int[rowCount][6];
		        for (int i = 0; i < rowCount; i++) {
		            processes[i][0] = Integer.parseInt(model.getValueAt(i, 0).toString()); 
		            processes[i][1] = Integer.parseInt(model.getValueAt(i, 2).toString()); 
		            processes[i][2] = Integer.parseInt(model.getValueAt(i, 3).toString()); 
		        }

		        
		        for (int i = 0; i < rowCount - 1; i++) {
		            for (int j = 0; j < rowCount - i - 1; j++) {
		                if (processes[j][1] > processes[j + 1][1]) {
		                    int[] temp = processes[j];
		                    processes[j] = processes[j + 1];
		                    processes[j + 1] = temp;
		                }
		            }
		        }

		        int time = processes[0][1];
		        while (true) {
		            int minIndex = -1;
		            int minBurstTime = Integer.MAX_VALUE;

		            
		            for (int i = 0; i < rowCount; i++) {
		                if (processes[i][2] > 0 && processes[i][1] <= time && processes[i][2] < minBurstTime) {
		                    minBurstTime = processes[i][2];
		                    minIndex = i;
		                }
		            }

		            if (minIndex == -1) { 
		                boolean allDone = true;
		                for (int i = 0; i < rowCount; i++) {
		                    if (processes[i][2] > 0) {
		                        allDone = false;
		                        break;
		                    }
		                }
		                if (allDone) break; 
		                time++;
		                continue;
		            }

		            
		            processes[minIndex][2]--; 
		            time++;

		            if (processes[minIndex][2] == 0) { 
		                processes[minIndex][3] = time; 
		                processes[minIndex][4] = processes[minIndex][3] - processes[minIndex][1]; 
		                processes[minIndex][5] = processes[minIndex][4] - Integer.parseInt(model.getValueAt(processes[minIndex][0] - 1, 3).toString()); // Waiting Time
		                model.setValueAt(processes[minIndex][3], processes[minIndex][0] - 1, 4);
		                model.setValueAt(processes[minIndex][4], processes[minIndex][0] - 1, 5);
		                model.setValueAt(processes[minIndex][5], processes[minIndex][0] - 1, 6);
		            }
		        }

		        JOptionPane.showMessageDialog(frame, "SJF Preemptive calculation applied successfully.");
		    }
		});

		btnClear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        model.setRowCount(0); 
		        JOptionPane.showMessageDialog(frame, "Table cleared successfully.");
		    }
		});

		ROUND_ROBIN.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        int rowCount = model.getRowCount();
		        if (rowCount == 0) {
		            JOptionPane.showMessageDialog(frame, "No processes available for Round Robin calculation.");
		            return;
		        }
		        int quantum;
		        try {
		            quantum = Integer.parseInt(textField_4.getText());
		            if (quantum <= 0) {
		                JOptionPane.showMessageDialog(frame, "Quantum must be a positive integer.");
		                return;
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(frame, "Invalid quantum value. Please enter a valid integer.");
		            return;
		        }
		        LinkedList<int[]> queue = new LinkedList<>();
		        for (int i = 0; i < rowCount; i++) {
		            int pid = Integer.parseInt(model.getValueAt(i, 0).toString());
		            int at = Integer.parseInt(model.getValueAt(i, 2).toString());
		            int bt = Integer.parseInt(model.getValueAt(i, 3).toString());
		            queue.add(new int[]{pid, at, bt, 0, 0, 0});
		        }
		        int time = queue.peekFirst()[1];
		        while (!queue.isEmpty()) {
		            int[] process = queue.poll();
		            if (process[2] > quantum) {
		                time += quantum;
		                process[2] -= quantum;
		                queue.add(new int[]{process[0], time, process[2], 0, 0, 0});
		            } else {
		                time += process[2];
		                process[3] = time;
		                process[4] = process[3] - process[1];
		                process[5] = process[4] - (Integer.parseInt(model.getValueAt(process[0] - 1, 3).toString()) - process[2]); // Waiting Time
		                model.setValueAt(process[3], process[0] - 1, 4);
		                model.setValueAt(process[4], process[0] - 1, 5);
		                model.setValueAt(process[5], process[0] - 1, 6);
		            }
		        }	
		        JOptionPane.showMessageDialog(frame, "Round Robin calculation applied successfully.");
		    }
		});


		
		
	}
	
	
}

