package os_lab2;

import java.sql.*;
import java.util.*;
public class sql{
//	public void connectionsql(String id,String N,String p) {
//
//	Scanner sc=new Scanner(System.in);
//	Scanner sc1=new Scanner(System.in);
//	
//
//	Connection connectionUrl=null;
//	Statement st;
//	PreparedStatement pst;
//	ResultSet rs ;
//	
//	try {
//		connectionUrl=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-2EBFQ8R\\VE_SERVER;databaseName=OSDB;user=sa;password=123456789;encrypt=false");
//		System.out.println("Connection Established");
//		String q1="Insert into task values(?,?,?)";
//		pst=connectionUrl.prepareStatement(q1);
//		pst.setString(1, id);
//		pst.setString(2, N);
//		pst.setString(3,p);
//		
//		pst.executeUpdate();
	public void connectionsql(String n) {

		Scanner sc=new Scanner(System.in);
		Scanner sc1=new Scanner(System.in);
		

		Connection connectionUrl=null;
		Statement st;
		PreparedStatement pst;
		ResultSet rs ;
		
		try {
			connectionUrl=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-2EBFQ8R\\VE_SERVER;databaseName=OSDB;user=sa;password=123456789;encrypt=false");
			System.out.println("Connection Established");
			String q1="INSERT INTO [dbo].[namer]\r\n"
					+ "           ([namee])\r\n"
					+ "     VALUES\r\n"
					+ "           (?)";
			pst=connectionUrl.prepareStatement(q1);		
			pst.setString(1, n);
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
	public String display() {

		Scanner sc=new Scanner(System.in);
		Scanner sc1=new Scanner(System.in);
		

		Connection connectionUrl=null;
		Statement st;
		PreparedStatement pst;
		ResultSet rs ;
		
		try {
			connectionUrl=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-2EBFQ8R\\VE_SERVER;databaseName=OSDB;user=sa;password=123456789;encrypt=false");
			System.out.println("Connection Established");
			String q1="select Namee from namer where id=(select max(id) from namer)";
			st=connectionUrl.createStatement();
			String name="";
			rs=st.executeQuery(q1);
			while(rs.next())
			{
				name=rs.getString(1);
			}
			return name;
		
	} catch (SQLException e) {
        System.out.println("An error occurred while connecting to the database: " + e.getMessage());
        return "";
    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
        return "";
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

