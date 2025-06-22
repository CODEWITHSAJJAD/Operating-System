package OS;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BOOKRegisterationFORM {

    private JFrame frame;
    private JTextField textBookId;
    private JTextField textBookTitle;
    private JTextField textAuthorName;
    private JTextField textPublicationDate;
    private JTextField textSearch;
    private JComboBox<String> searchCriteria;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BOOKRegisterationFORM window = new BOOKRegisterationFORM();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BOOKRegisterationFORM() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Book Registration Form");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblHeader = new JLabel("BOOK REGISTRATION FORM");
        lblHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblHeader.setBounds(250, 20, 300, 30);
        frame.getContentPane().add(lblHeader);

        JLabel lblBookId = new JLabel("Book ID:");
        lblBookId.setBounds(50, 80, 100, 25);
        frame.getContentPane().add(lblBookId);

        textBookId = new JTextField();
        textBookId.setBounds(150, 80, 200, 25);
        frame.getContentPane().add(textBookId);
        textBookId.setColumns(10);

        JLabel lblBookTitle = new JLabel("Book Title:");
        lblBookTitle.setBounds(50, 120, 100, 25);
        frame.getContentPane().add(lblBookTitle);

        textBookTitle = new JTextField();
        textBookTitle.setBounds(150, 120, 200, 25);
        frame.getContentPane().add(textBookTitle);
        textBookTitle.setColumns(10);

        JLabel lblAuthorName = new JLabel("Author Name:");
        lblAuthorName.setBounds(50, 160, 100, 25);
        frame.getContentPane().add(lblAuthorName);

        textAuthorName = new JTextField();
        textAuthorName.setBounds(150, 160, 200, 25);
        frame.getContentPane().add(textAuthorName);
        textAuthorName.setColumns(10);

        JLabel lblPublicationDate = new JLabel("Publication Date:");
        lblPublicationDate.setBounds(50, 200, 120, 25);
        frame.getContentPane().add(lblPublicationDate);

        textPublicationDate = new JTextField();
        textPublicationDate.setBounds(180, 200, 170, 25);
        frame.getContentPane().add(textPublicationDate);
        textPublicationDate.setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(50, 250, 100, 30);
        frame.getContentPane().add(btnAdd);

        JButton btnDisplay = new JButton("Display");
        btnDisplay.setBounds(180, 250, 100, 30);
        frame.getContentPane().add(btnDisplay);

        JLabel lblSearchCriteria = new JLabel("Search by:");
        lblSearchCriteria.setBounds(50, 300, 100, 25);
        frame.getContentPane().add(lblSearchCriteria);

        searchCriteria = new JComboBox<>(new String[]{"Title", "Author", "Publication Date"});
        searchCriteria.setBounds(150, 300, 120, 25);
        frame.getContentPane().add(searchCriteria);

        textSearch = new JTextField();
        textSearch.setBounds(280, 300, 150, 25);
        frame.getContentPane().add(textSearch);
        textSearch.setColumns(10);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(450, 300, 100, 30);
        frame.getContentPane().add(btnSearch);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 350, 700, 200);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int bookId = Integer.parseInt(textBookId.getText());
                String bookTitle = textBookTitle.getText();
                String authorName = textAuthorName.getText();
                String publicationDate = textPublicationDate.getText();
                insertBookRecord(bookId, bookTitle, authorName, publicationDate);
            }
        });

        btnDisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAllBooks();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String criteria = (String) searchCriteria.getSelectedItem();
                String query = textSearch.getText();
                searchBooks(criteria, query);
            }
        });
    }

    private Connection connectToDatabase() {
        String url = "jdbc:sqlserver://SUQOON\\SUQOON;databaseName=OS;user=sa;password=123456;encrypt=false;";

        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void insertBookRecord(int bookId, String bookTitle, String authorName, String publicationDate) {
        String sql = "INSERT INTO BOOK (Book_id, Book_Title, Author_Name, Publication_Date) VALUES (?, ?, ?, ?)";

        try (Connection connection = connectToDatabase();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            if (connection != null) {
                pst.setInt(1, bookId);
                pst.setString(2, bookTitle);
                pst.setString(3, authorName);
                pst.setString(4, publicationDate);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(frame, "Book record inserted successfully!");
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayAllBooks() {
        String sql = "SELECT * FROM BOOK";

        try (Connection connection = connectToDatabase();
             PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (connection != null) {
                DefaultTableModel model = new DefaultTableModel(new String[]{"Book ID", "Title", "Author", "Publication Date"}, 0);

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("Book_id"),
                        rs.getString("Book_Title"),
                        rs.getString("Author_Name"),
                        rs.getString("Publication_Date")
                    });
                }

                table.setModel(model);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchBooks(String criteria, String query) {
        String sql = "";

        switch (criteria) {
            case "Title":
                sql = "SELECT * FROM BOOK WHERE Book_Title LIKE ?";
                break;
            case "Author":
                sql = "SELECT * FROM BOOK WHERE Author_Name LIKE ?";
                break;
            case "Publication Date":
                sql = "SELECT * FROM BOOK WHERE Publication_Date = ?";
                break;
        }

        try (Connection connection = connectToDatabase();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            if (connection != null) {
                if (criteria.equals("Publication Date")) {
                    pst.setString(1, query);
                } else {
                    pst.setString(1, "%" + query + "%");
                }

                try (ResultSet rs = pst.executeQuery()) {
                    DefaultTableModel model = new DefaultTableModel(new String[]{"Book ID", "Title", "Author", "Publication Date"}, 0);

                    if (!rs.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(frame, "ja ja tur ja", "No Records Found", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    while (rs.next()) {
                        model.addRow(new Object[]{
                            rs.getInt("Book_id"),
                            rs.getString("Book_Title"),
                            rs.getString("Author_Name"),
                            rs.getString("Publication_Date")
                        });
                    }

                    table.setModel(model);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}