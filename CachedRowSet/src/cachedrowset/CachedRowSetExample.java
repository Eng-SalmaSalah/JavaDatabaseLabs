/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachedrowset;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author salma
 */
public class CachedRowSetExample {

    CachedRowSet cachedRowset;

    public CachedRowSetExample() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDB", "root", "salma");
            connection.setAutoCommit(false);
            cachedRowset = new CachedRowSetImpl();
            cachedRowset.setUsername("root");
            cachedRowset.setPassword("salma");
            cachedRowset.setUrl("jdbc:mysql://localhost:3306/employeeDB");
            cachedRowset.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            cachedRowset.setConcurrency(ResultSet.CONCUR_UPDATABLE);

            printCachedRowSet();
            cachedRowset.absolute(2);
            cachedRowset.updateString(2, "menna");
            cachedRowset.updateRow();
            cachedRowset.acceptChanges(connection);
            System.out.println("table after update:");
            printCachedRowSet();

            cachedRowset.moveToInsertRow();
            cachedRowset.updateInt(1, 600);
            cachedRowset.updateString(2, "mai");
            cachedRowset.updateInt(3, 49000);
            cachedRowset.updateInt(4, 19380);
            cachedRowset.insertRow();
            cachedRowset.moveToCurrentRow();
            cachedRowset.acceptChanges(connection); 
            System.out.println("table after insert:");
            printCachedRowSet();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CachedRowSetExample();
    }

    private void printCachedRowSet() {
        try {
            cachedRowset.setCommand("select * from Employee");
            cachedRowset.execute();
            while (cachedRowset.next()) {
                System.out.println("employee ID: " + cachedRowset.getInt("EmpID") + " " + "emplyee name: " + cachedRowset.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
