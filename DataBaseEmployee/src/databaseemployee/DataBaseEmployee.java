/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseemployee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author salma
 */
public class DataBaseEmployee {

    /**
     * @param args the command line arguments
     */
    public DataBaseEmployee() {
        try {
            int done;
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDB",
                    "root", "salma");
            Statement statment = connection.createStatement();
            String insertionQuery=new String("INSERT INTO Employee VALUES(3, 'ahmed', 20000, 40)");
            done = statment.executeUpdate(insertionQuery);
            String query = new String("select * from Employee");
            ResultSet resultset = statment.executeQuery(query);
            while (resultset.next()) {
                System.out.println("employee ID: "+resultset.getInt("EmpID")+" " +"emplyee name: "+ resultset.getString(2));
            }
            
            statment.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DataBaseEmployee();
    }
}
