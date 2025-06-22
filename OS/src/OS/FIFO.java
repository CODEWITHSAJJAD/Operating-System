package OS;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

public class FIFO {

    private JFrame frame;
    private JTextField numPagesField;
    private JTextField pageSizeField;
    private JTextField memorySizeField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextArea resultArea;

    // Database connection details
    private static final String DB_URL = "jdbc:sqlserver://SUQOON\\SUQOON;databaseName=OS;user=sa;password=123456;encrypt=false;";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

    public FIFO() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("FIFO Page Replacement Algorithm");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNumPages = new JLabel("Number of Pages:");
        lblNumPages.setBounds(20, 20, 150, 25);
        frame.getContentPane().add(lblNumPages);

        numPagesField = new JTextField();
        numPagesField.setBounds(180, 20, 150, 25);
        frame.getContentPane().add(numPagesField);

        JLabel lblPageSize = new JLabel("Page Size (KB):");
        lblPageSize.setBounds(20, 60, 150, 25);
        frame.getContentPane().add(lblPageSize);

        pageSizeField = new JTextField();
        pageSizeField.setBounds(180, 60, 150, 25);
        frame.getContentPane().add(pageSizeField);

        JLabel lblMemorySize = new JLabel("Memory Size (KB):");
        lblMemorySize.setBounds(20, 100, 150, 25);
        frame.getContentPane().add(lblMemorySize);

        memorySizeField = new JTextField();
        memorySizeField.setBounds(180, 100, 150, 25);
        frame.getContentPane().add(memorySizeField);

        JButton btnRun = new JButton("Run");
        btnRun.setBounds(20, 140, 100, 25);
        frame.getContentPane().add(btnRun);

        tableModel = new DefaultTableModel(new String[]{"Step", "Page Reference", "Frames"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(20, 180, 740, 200);
        frame.getContentPane().add(tableScrollPane);

        JLabel lblResults = new JLabel("Results:");
        lblResults.setBounds(20, 400, 150, 25);
        frame.getContentPane().add(lblResults);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultScrollPane.setBounds(20, 430, 740, 100);
        frame.getContentPane().add(resultScrollPane);

        btnRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runFIFOAlgorithm();
            }
        });
    }

    private void runFIFOAlgorithm() {
        try {
            int numPages = Integer.parseInt(numPagesField.getText());
            int pageSize = Integer.parseInt(pageSizeField.getText());
            int memorySize = Integer.parseInt(memorySizeField.getText());

            if (memorySize < pageSize) {
                JOptionPane.showMessageDialog(frame, "Memory size cannot be smaller than page size.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int numFrames = memorySize / pageSize;

            // Generate random page references
            int[] pageReferences = new int[numPages];
            for (int i = 0; i < numPages; i++) {
                pageReferences[i] = (int) (Math.random() * 10);
            }

            // Run FIFO algorithm
            Queue<Integer> frameQueue = new LinkedList<>();
            int pageFaults = 0;

            tableModel.setRowCount(0); // Clear previous results

            for (int i = 0; i < pageReferences.length; i++) {
                int page = pageReferences[i];

                if (!frameQueue.contains(page)) {
                    if (frameQueue.size() >= numFrames) {
                        frameQueue.poll(); // Remove the oldest page
                    }
                    frameQueue.add(page);
                    pageFaults++;
                }

                // Update table
                tableModel.addRow(new Object[]{
                        i + 1,
                        page,
                        frameQueue.toString()
                });
            }

            // Display results
            resultArea.setText("Page References: " + java.util.Arrays.toString(pageReferences) + "\n");
            resultArea.append("Total Page Faults: " + pageFaults + "\n");

            // Save results to the database
            saveResultsToDatabase(pageReferences, pageFaults);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveResultsToDatabase(int[] pageReferences, int pageFaults) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String insertQuery = "INSERT INTO FIFO (id,page_references, page_faults) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, java.util.Arrays.toString(pageReferences));
            preparedStatement.setInt(2, pageFaults);
            preparedStatement.executeUpdate();

            resultArea.append("\nResults saved to the database successfully.");
        } catch (SQLException ex) {
            resultArea.append("\nFailed to save results to the database: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FIFO window = new FIFO();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}