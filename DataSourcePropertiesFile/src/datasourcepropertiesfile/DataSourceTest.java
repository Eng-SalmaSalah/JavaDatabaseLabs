/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasourcepropertiesfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author salma
 */
public class DataSourceTest {

    /**
     * @param args the command line arguments
     */
    public DataSourceTest() {
        DataSource dataSource = null;
        dataSource = DataSourceFactory.getMySQLDataSource();
        Connection connection = null;
        Statement statment = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statment = connection.createStatement();
            resultSet = statment.executeQuery("select * from Employee");
            while (resultSet.next()) {
                System.out.println("employee ID: " + resultSet.getInt("EmpID") + " " + "emplyee name: " + resultSet.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
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
        new DataSourceTest();
    }

}
