/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcrowset;

import com.sun.rowset.JdbcRowSetImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author salma
 */
public class JDBCRowSet {

    DataSource dataSource = null;
    Connection connection = null;
    Statement statment = null;
    ResultSet resultSet = null;
    JdbcRowSetImpl jdbcRowSet=null;

    public JDBCRowSet() {

        dataSource = DataSourceFactory.getMySQLDataSource();
        try {
            connection = dataSource.getConnection();
            statment = connection.createStatement();
            resultSet = statment.executeQuery("select * from Employee");
            jdbcRowSet = new JdbcRowSetImpl(resultSet);
            while (jdbcRowSet.next()) {
                System.out.println("employee ID: " + jdbcRowSet.getInt("EmpID") + " " + "emplyee name: " + jdbcRowSet.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (jdbcRowSet != null) {
                    jdbcRowSet.close();
                }
                if (statment != null) {
                    statment.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new JDBCRowSet();
    }

}
