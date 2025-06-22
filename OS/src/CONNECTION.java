import java.sql.*;
public class CONNECTION {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String url = "jdbc:sqlserver://DESKTOP-2GIJV0P\\SUQOON;databaseName=OS;user=sa;password=123456;encrypt=false;";
        String username = "sa";
        String password = "123456";
        Connection connection = null;		
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established.");

        }
        catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());

	}

}
}