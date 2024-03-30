package act.no2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class JDBCDemo {
    public static Connection myConn = null;
    public static PreparedStatement myPStmt = null;
    public static ResultSet myRs = null;
    
    public static void main(String[] args) {
        connectDB();
        
        FrmStudentList fm = new FrmStudentList();
        fm.show();
    }
    
    public static void connectDB(){
        try {            
            Class.forName("com.mysql.jdbc.Driver"); 
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/information", "root", "");
            JOptionPane.showMessageDialog(null,"Connection Successfully Established.","Success", JOptionPane.INFORMATION_MESSAGE);            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"Database Connection Error." + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);            
        }
    }
    
    public static void selectDB(){
        try {            
            // Query which needs parameters
            String query = "Select * from tblstudent";
 
            // Prepare Statement
            myPStmt = myConn.prepareStatement(query);

            // Set Parameters
            myPStmt.setInt(1, 2);
            //myPStmt.setString(2, "xx");

            // Execute SQL query
            myRs = myPStmt.executeQuery();

            System.out.println("EmployeeID      EmployeeName");

            // Display function to show the Resultset
            while (myRs.next()) {
                int id = myRs.getInt("EmployeeID");
                String name = myRs.getString("EmployeeName");
                System.out.println(id + "     " + name);
            }

            // Close the connection
            myConn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"SQL Error." + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);            
        }
    }
}
