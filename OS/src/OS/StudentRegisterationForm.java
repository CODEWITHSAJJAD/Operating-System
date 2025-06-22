package OS;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentRegisterationForm {

	private JFrame frame;
	private JTextField ARID_NO;
	private JTextField NAME;
	private JTextField textField_2;
	private JTable table;
	private final String url = "jdbc:sqlserver://SUQOON\\SUQOON;databaseName=OS;user=sa;password=123456;encrypt=false;";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentRegisterationForm window = new StudentRegisterationForm();
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
	public StudentRegisterationForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 670, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		ARID_NO = new JTextField();
		frame.getContentPane().add(ARID_NO);
		ARID_NO.setColumns(10);
		
		NAME = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, ARID_NO, 0, SpringLayout.EAST, NAME);
		springLayout.putConstraint(SpringLayout.NORTH, NAME, 97, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, NAME, 105, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, NAME, -444, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ARID_NO, -30, SpringLayout.NORTH, NAME);
		NAME.setColumns(10);
		frame.getContentPane().add(NAME);
		
		JLabel lblNewLabel = new JLabel("ARID NO:");
		springLayout.putConstraint(SpringLayout.WEST, ARID_NO, 2, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 48, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 44, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblName = new JLabel("NAME:");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 30, SpringLayout.SOUTH, ARID_NO);
		springLayout.putConstraint(SpringLayout.WEST, lblName, 62, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblName, 17, SpringLayout.WEST, NAME);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblName);
		
		JLabel lblDiscipline = new JLabel("DISCIPLINE:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDiscipline, 44, SpringLayout.SOUTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblDiscipline, 29, SpringLayout.WEST, frame.getContentPane());
		lblDiscipline.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblDiscipline);
		
		JComboBox DISCIPLINE = new JComboBox<>(new String[]{"AI","CS", "SE"});
		springLayout.putConstraint(SpringLayout.NORTH, DISCIPLINE, 152, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, DISCIPLINE, 2, SpringLayout.EAST, lblDiscipline);
		springLayout.putConstraint(SpringLayout.EAST, DISCIPLINE, -443, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(DISCIPLINE);
		
		JLabel lblGender = new JLabel("GENDER:");
		springLayout.putConstraint(SpringLayout.WEST, lblGender, 230, SpringLayout.WEST, frame.getContentPane());
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblGender);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("MALE");
		springLayout.putConstraint(SpringLayout.WEST, rdbtnNewRadioButton, 6, SpringLayout.EAST, lblGender);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnFemale = new JRadioButton("FEMALE");
		springLayout.putConstraint(SpringLayout.WEST, rdbtnFemale, 13, SpringLayout.EAST, rdbtnNewRadioButton);
		rdbtnFemale.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(rdbtnFemale);
		ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rdbtnNewRadioButton);
        genderGroup.add(rdbtnFemale);

		
		JCheckBox chckbxNewCheckBox = new JCheckBox("OS");
		springLayout.putConstraint(SpringLayout.NORTH, chckbxNewCheckBox, -1, SpringLayout.NORTH, NAME);
		springLayout.putConstraint(SpringLayout.EAST, chckbxNewCheckBox, -323, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxCa = new JCheckBox("CA");
		springLayout.putConstraint(SpringLayout.NORTH, chckbxCa, -1, SpringLayout.NORTH, NAME);
		springLayout.putConstraint(SpringLayout.WEST, chckbxCa, 16, SpringLayout.EAST, chckbxNewCheckBox);
		frame.getContentPane().add(chckbxCa);
		
		JCheckBox chckbxHcicg = new JCheckBox("HCICG");
		frame.getContentPane().add(chckbxHcicg);
		
		JCheckBox chckbxAnndl = new JCheckBox("ANNDL");
		springLayout.putConstraint(SpringLayout.NORTH, chckbxHcicg, 6, SpringLayout.SOUTH, chckbxAnndl);
		springLayout.putConstraint(SpringLayout.WEST, chckbxHcicg, 0, SpringLayout.WEST, chckbxAnndl);
		springLayout.putConstraint(SpringLayout.WEST, chckbxAnndl, 11, SpringLayout.EAST, chckbxCa);
		springLayout.putConstraint(SpringLayout.NORTH, chckbxAnndl, -1, SpringLayout.NORTH, NAME);
		frame.getContentPane().add(chckbxAnndl);
		
		JCheckBox chckbxNlp = new JCheckBox("NLP");
		springLayout.putConstraint(SpringLayout.NORTH, chckbxNlp, 6, SpringLayout.SOUTH, chckbxNewCheckBox);
		springLayout.putConstraint(SpringLayout.WEST, chckbxNlp, 0, SpringLayout.WEST, rdbtnNewRadioButton);
		frame.getContentPane().add(chckbxNlp);
		
		JCheckBox chckbxToa = new JCheckBox("TOA");
		springLayout.putConstraint(SpringLayout.NORTH, chckbxToa, 6, SpringLayout.SOUTH, chckbxCa);
		springLayout.putConstraint(SpringLayout.WEST, chckbxToa, 0, SpringLayout.WEST, chckbxCa);
		frame.getContentPane().add(chckbxToa);
		
		JButton btnADD = new JButton("ADD");
		springLayout.putConstraint(SpringLayout.NORTH, btnADD, -2, SpringLayout.NORTH, lblDiscipline);
		springLayout.putConstraint(SpringLayout.WEST, btnADD, 29, SpringLayout.EAST, DISCIPLINE);
		frame.getContentPane().add(btnADD);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnSearch);
		
		JButton btnDisplay = new JButton("DISPLAY");
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, 0, SpringLayout.EAST, btnDisplay);
		springLayout.putConstraint(SpringLayout.EAST, btnADD, -13, SpringLayout.WEST, btnDisplay);
		springLayout.putConstraint(SpringLayout.NORTH, btnDisplay, -2, SpringLayout.NORTH, lblDiscipline);
		springLayout.putConstraint(SpringLayout.WEST, btnDisplay, 0, SpringLayout.WEST, chckbxCa);
		frame.getContentPane().add(btnDisplay);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, -1, SpringLayout.NORTH, textField_2);
		springLayout.putConstraint(SpringLayout.WEST, textField_2, -94, SpringLayout.EAST, chckbxNewCheckBox);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, chckbxNewCheckBox);
		textField_2.setColumns(10);
		frame.getContentPane().add(textField_2);
		
		JLabel lblStudentRegisterationForm = new JLabel("STUDENT REGISTERATION FORM");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnFemale, 15, SpringLayout.SOUTH, lblStudentRegisterationForm);
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnNewRadioButton, 15, SpringLayout.SOUTH, lblStudentRegisterationForm);
		springLayout.putConstraint(SpringLayout.NORTH, lblGender, 19, SpringLayout.SOUTH, lblStudentRegisterationForm);
		springLayout.putConstraint(SpringLayout.NORTH, lblStudentRegisterationForm, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStudentRegisterationForm, 124, SpringLayout.WEST, frame.getContentPane());
		lblStudentRegisterationForm.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(lblStudentRegisterationForm);
		
		JLabel lblCourses = new JLabel("COURSES:");
		springLayout.putConstraint(SpringLayout.WEST, chckbxNewCheckBox, 5, SpringLayout.EAST, lblCourses);
		springLayout.putConstraint(SpringLayout.NORTH, lblCourses, 1, SpringLayout.NORTH, NAME);
		springLayout.putConstraint(SpringLayout.EAST, lblCourses, 0, SpringLayout.EAST, lblGender);
		lblCourses.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblCourses);
		
		JLabel lblSearchBy = new JLabel("SEARCH BY:");
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, -1, SpringLayout.NORTH, lblSearchBy);
		springLayout.putConstraint(SpringLayout.NORTH, lblSearchBy, 30, SpringLayout.SOUTH, lblDiscipline);
		springLayout.putConstraint(SpringLayout.EAST, lblSearchBy, 0, SpringLayout.EAST, lblNewLabel);
		lblSearchBy.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblSearchBy);
		
		JComboBox SEARCH = new JComboBox<>(new String[]{"NAME", "ARID NO","DESCIPLINE","GENDER"});
		springLayout.putConstraint(SpringLayout.NORTH, SEARCH, -2, SpringLayout.NORTH, lblSearchBy);
		springLayout.putConstraint(SpringLayout.WEST, SEARCH, 0, SpringLayout.WEST, ARID_NO);
		springLayout.putConstraint(SpringLayout.EAST, SEARCH, 0, SpringLayout.EAST, ARID_NO);
		frame.getContentPane().add(SEARCH);
		
		String [] columnNames= {"ARID NO", "NAME", "GENDER", "DISCIPLINE", "COURSES"};
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"ARID NO", "NAME", "GENDER", "DISCIPLINE", "COURSES"}
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
		
		JButton btnDelete = new JButton("DELETE");
		springLayout.putConstraint(SpringLayout.NORTH, btnDelete, 0, SpringLayout.NORTH, btnSearch);
		frame.getContentPane().add(btnDelete);
		
		JButton btnUpdate = new JButton("UPDATE");
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, 0, SpringLayout.WEST, btnUpdate);
		springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, 0, SpringLayout.NORTH, lblDiscipline);
		springLayout.putConstraint(SpringLayout.WEST, btnUpdate, 22, SpringLayout.EAST, btnSearch);
		frame.getContentPane().add(btnUpdate);
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection conn = DriverManager.getConnection(url)) {
					Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
                    DefaultTableModel model=(DefaultTableModel) table.getModel();                    
                    model.setRowCount(0); 
                    String arid,name,gender,descipline,courses;
                    while(rs.next()) {
                    	arid=rs.getString(1);
                    	name=rs.getString(2);
                    	gender=rs.getString(3);
                    	descipline=rs.getString(4);
                    	courses=rs.getString(5);                    	
                    	String row []= {arid,name,gender,descipline,courses};
                    	model.addRow(row);
                    	
                    }

					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnADD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String arid = ARID_NO.getText();
                String name = NAME.getText();
                String discipline = (String) DISCIPLINE.getSelectedItem();
                String gender = rdbtnNewRadioButton.isSelected() ? "MALE" : rdbtnFemale.isSelected() ? "FEMALE" : "";
                StringBuilder courses = new StringBuilder();

                if (chckbxNewCheckBox.isSelected()) courses.append("OS, ");
                if (chckbxCa.isSelected()) courses.append("CA, ");
                if (chckbxNlp.isSelected()) courses.append("NLP, ");
                if (chckbxHcicg.isSelected()) courses.append("HCICG, ");
                if (chckbxAnndl.isSelected()) courses.append("ANNDL, ");
                if (chckbxToa.isSelected()) courses.append("TOA, ");
                if (courses.length() > 0) courses.setLength(courses.length() - 2); // Remove last comma

                
                try (Connection conn = DriverManager.getConnection(url)) {
                	String checkSql = "SELECT COUNT(*) FROM Student WHERE arid = ?";
                    PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                    checkStmt.setString(1, arid);
                    ResultSet rs = checkStmt.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(frame, "Error: Duplicate ARID. Please enter a unique ARID.", "Database Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String sql = "INSERT INTO Student (arid,name,gender,descipline,courses) VALUES (?, ?, ?, ?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, arid);
                    pstmt.setString(2, name);
                    pstmt.setString(3, gender);
                    pstmt.setString(4, discipline);
                    pstmt.setString(5, courses.toString());
                   
                    pstmt.executeUpdate();
                   
                    JOptionPane.showMessageDialog(frame, "Student details saved to database.");
                }catch (SQLException ex) {
                    if (ex.getErrorCode() == 2627 || ex.getErrorCode() == 2601) {
                        JOptionPane.showMessageDialog(frame, "Error: Duplicate ARID. Please enter a unique ARID.", "Database Error", JOptionPane.ERROR_MESSAGE);
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
		
		btnSearch.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String searchCategory = (String) SEARCH.getSelectedItem();
		        String searchText = textField_2.getText().trim();
		        if (searchText.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Please enter text to search.", "Search Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        String sql;
		        if (searchCategory.equals("GENDER")) {
		            sql = "SELECT * FROM Student WHERE " + searchCategory + " = ?";
		        } else {
		            if (searchCategory.equals("ARID NO")) {
		                searchCategory = "ARID";
		            }
		            sql = "SELECT * FROM Student WHERE " + searchCategory + " LIKE ?";
		        }

		        try (Connection conn = DriverManager.getConnection(url)) {
		            PreparedStatement pstmt = conn.prepareStatement(sql);
		            if (searchCategory.equals("GENDER")) {
		                pstmt.setString(1, searchText);
		            } else {
		                pstmt.setString(1, "%" + searchText + "%");
		            }
		            ResultSet rs = pstmt.executeQuery();
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            model.setRowCount(0);

		            while (rs.next()) {
		                String arid = rs.getString("arid");
		                String name = rs.getString("name");
		                String gender = rs.getString("gender");
		                String descipline = rs.getString("descipline");
		                String courses = rs.getString("courses");
		                model.addRow(new Object[]{arid, name, gender, descipline, courses});
		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});
		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            String arid = (String) table.getValueAt(selectedRow, 0);
		            String name = (String) table.getValueAt(selectedRow, 1);
		            String gender = (String) table.getValueAt(selectedRow, 2);
		            String descipline = (String) table.getValueAt(selectedRow, 3);
		            String courses = (String) table.getValueAt(selectedRow, 4);

		            try (Connection conn = DriverManager.getConnection(url)) {
		                String sql = "UPDATE Student SET name=?, gender=?, descipline=?, courses=? WHERE arid=?";
		                PreparedStatement pstmt = conn.prepareStatement(sql);
		                pstmt.setString(1, name);
		                pstmt.setString(2, gender);
		                pstmt.setString(3, descipline);
		                pstmt.setString(4, courses);
		                pstmt.setString(5, arid);
		                pstmt.executeUpdate();
		                JOptionPane.showMessageDialog(frame, "Student details updated successfully.");
		            } catch (SQLException ex) {
		                JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		                ex.printStackTrace();
		            }
		        } else {
		            JOptionPane.showMessageDialog(frame, "Please select a row to update.", "Update Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            String arid = (String) table.getValueAt(selectedRow, 0);
		            int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this student?", "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		            if (response == JOptionPane.YES_OPTION) {
		                try (Connection conn = DriverManager.getConnection(url)) {
		                    String sql = "DELETE FROM Student WHERE arid=?";
		                    PreparedStatement pstmt = conn.prepareStatement(sql);
		                    pstmt.setString(1, arid);
		                    pstmt.executeUpdate();
		                    ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
		                    JOptionPane.showMessageDialog(frame, "Student deleted successfully.");
		                } catch (SQLException ex) {
		                    JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		                    ex.printStackTrace();
		                }
		            }
		        } else {
		            JOptionPane.showMessageDialog(frame, "Please select a row to delete.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		           
		            ARID_NO.setText(table.getValueAt(selectedRow, 0).toString());
		            NAME.setText(table.getValueAt(selectedRow, 1).toString());		           
		            DISCIPLINE.setSelectedItem(table.getValueAt(selectedRow, 3).toString());
		            String gender = table.getValueAt(selectedRow, 2).toString();
		            if ("MALE".equals(gender)) {
		                rdbtnNewRadioButton.setSelected(true);
		            } else if ("FEMALE".equals(gender)) {
		                rdbtnFemale.setSelected(true);
		            }		           
		            String courses = table.getValueAt(selectedRow, 4).toString();
		            chckbxNewCheckBox.setSelected(courses.contains("OS"));
		            chckbxCa.setSelected(courses.contains("CA"));
		            chckbxHcicg.setSelected(courses.contains("HCICG"));
		            chckbxAnndl.setSelected(courses.contains("ANNDL"));
		            chckbxNlp.setSelected(courses.contains("NLP"));
		            chckbxToa.setSelected(courses.contains("TOA"));
		        }
		    }
		});




		
	}
}
