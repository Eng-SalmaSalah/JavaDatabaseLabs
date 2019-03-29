/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empdeptjoin;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.JoinRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;

public class EmpDeptJoin {

    CachedRowSet cachedRowsetEmp;
    CachedRowSet cachedRowsetDept;
    JoinRowSet joinRowSet;

    public EmpDeptJoin() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDB", "root", "salma");
            connection.setAutoCommit(false);
            cachedRowsetEmp = new CachedRowSetImpl();
            cachedRowsetEmp.setUsername("root");
            cachedRowsetEmp.setPassword("salma");
            cachedRowsetEmp.setUrl("jdbc:mysql://localhost:3306/employeeDB");
            cachedRowsetEmp.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            cachedRowsetEmp.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            cachedRowsetEmp.setCommand("select * from Employee");
            cachedRowsetEmp.execute();

            cachedRowsetDept = new CachedRowSetImpl();
            cachedRowsetDept.setUsername("root");
            cachedRowsetDept.setPassword("salma");
            cachedRowsetDept.setUrl("jdbc:mysql://localhost:3306/employeeDB");
            cachedRowsetDept.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            cachedRowsetDept.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            cachedRowsetDept.setCommand("select * from department");
            cachedRowsetDept.execute();

            joinRowSet = new JoinRowSetImpl();
            joinRowSet.addRowSet(cachedRowsetEmp, "deptID");
            joinRowSet.addRowSet(cachedRowsetDept, "deptID");

            while (joinRowSet.next()) {
                System.out.println("emp id: " + joinRowSet.getInt(1));
                System.out.println("emp name: " + joinRowSet.getString(2));
                System.out.println("emp salary: " + joinRowSet.getInt(3));
                System.out.println("dept id: " + joinRowSet.getInt(4));
                System.out.println("dept name: " + joinRowSet.getString(5));
                System.out.println("dept manager: " + joinRowSet.getString(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EmpDeptJoin();
    }

}
