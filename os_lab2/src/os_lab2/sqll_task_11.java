package os_lab2;

import java.sql.SQLException;
import java.sql.*;
import java.util.*;
public class sqll_task_11 {
	public void connectionsql(String id,String N,String p) {

	Scanner sc=new Scanner(System.in);
	Scanner sc1=new Scanner(System.in);
	

	Connection connectionUrl=null;
	Statement st;
	PreparedStatement pst;
	ResultSet rs ;
	
	try {
		connectionUrl=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-2EBFQ8R\\VE_SERVER;databaseName=OSDB;user=sa;password=123456789;encrypt=false");
		System.out.println("Connection Established");
		String q1="Insert into task values(?,?,?)";
		pst=connectionUrl.prepareStatement(q1);
		pst.setString(1, id);
		pst.setString(2, N);
		pst.setString(3,p);
		
		pst.executeUpdate();
	} catch (SQLException e) {
        System.out.println("An error occurred while connecting to the database: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    } finally {
        try {
            if (connectionUrl != null)
                connectionUrl.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while closing the database connection: " + e.getMessage());
        }

}

}

}
