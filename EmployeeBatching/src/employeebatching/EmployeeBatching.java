/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeebatching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author salma
 */
public class EmployeeBatching {

    DataSource dataSource = null;
    Connection connection = null;
    Statement statment = null;
    PreparedStatement preparedStatment;
    ResultSet resultSet = null;
    String creationQuery;
    String insertionQuery;

    public EmployeeBatching() {
        try {
            dataSource = DataSourceFactory.getMySQLDataSource();
            connection = dataSource.getConnection();
            createTable();
            insertIntoTable();
            showTable();
            modifyTable();
            System.out.println("table after modification: ");
            showTable();
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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void createTable() {
        try {
            statment = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            creationQuery = "CREATE TABLE IF NOT EXISTS EMPLOYEE (Id int ,F_Name varchar(30), L_Name varchar(30), Sex varchar(10), Age int, Address  varchar(100), Phone_Number varchar(30),Vaction_Balance int DEFAULT 30);";
            statment.executeUpdate(creationQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void insertValues(int id, String fname, String lname, String gender, int age, String address, String phoneNum) {
        try {
            preparedStatment.setInt(1, id);
            preparedStatment.setString(2, fname);
            preparedStatment.setString(3, lname);
            preparedStatment.setString(4, gender);
            preparedStatment.setInt(5, age);
            preparedStatment.setString(6, address);
            preparedStatment.setString(7, phoneNum);
            preparedStatment.addBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void showTable() {

        try {
            statment = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statment.executeQuery("select * from EMPLOYEE");
            while (resultSet.next()) {
                System.out.println("employee ID: " + resultSet.getInt(1) + "\n " + "employee first name: " + resultSet.getString(2) + "\n " + "emplyee last name: " + resultSet.getString(3) + "\n " + "sex: " + resultSet.getString(4) + "\n " + "age: " + resultSet.getInt(5) + "\n " + "emplyee address: " + resultSet.getString(6) + "\n " + "emplyee phoneNum: " + resultSet.getString(7) + "\n " + "Vacation_balance: " + resultSet.getInt(8));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void insertIntoTable() {
        try {
            insertionQuery = "INSERT INTO EMPLOYEE (Id,F_Name,L_Name,Sex,Age,Address,Phone_Number)VALUES ( ? , ? , ? , ? , ? , ? , ?);";
            preparedStatment = connection.prepareStatement(insertionQuery);
            connection.setAutoCommit(false);
            insertValues(1, "salma", "salah", "female", 24, "nasrCity", "0102176112");
            insertValues(2, "ahmed", "mohamed", "male", 40, "maadi", "0106935112");
            insertValues(3, "radya", "elbasha", "female", 22, "mokatam", "0168651192");
            insertValues(4, "nouran", "amr", "female", 50, "helwan", "01136984212");
            insertValues(5, "islam", "mohsen", "male", 30, "roksi", "01069321482");
            preparedStatment.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void modifyTable() {
        try {
            connection.setAutoCommit(false);
            statment = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String updateVacationBalance = "update EMPLOYEE set Vaction_Balance=45 where Age>45;";
            statment.addBatch(updateVacationBalance);
            String updateMalesName = "update EMPLOYEE set F_Name= concat('Mr ',F_Name )where Sex='male';";
            statment.addBatch(updateMalesName);
            String updateFemalesName = "update EMPLOYEE set F_Name= concat('Mrs ',F_Name )where Sex='female';";
            statment.addBatch(updateFemalesName);
            int[] count = statment.executeBatch();
            connection.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EmployeeBatching();
    }

}
