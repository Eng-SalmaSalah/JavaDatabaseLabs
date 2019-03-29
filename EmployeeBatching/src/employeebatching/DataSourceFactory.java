/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeebatching;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;

/**
 *
 * @author salma
 */
public class DataSourceFactory {

    public static DataSource getMySQLDataSource() {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        MysqlDataSource mysqlDataSource = null;
        try {

            fileInputStream = new FileInputStream("C:\\Users\\salma\\Documents\\NetBeansProjects\\EmployeeBatching\\propertiesFile.properties");
            properties.load(fileInputStream);
            mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL(properties.getProperty(
                    "URL"));
            mysqlDataSource.setUser(properties.getProperty(
                    "USERNAME"));
            mysqlDataSource.setPassword(properties.getProperty(
                    "PASSWORD"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return mysqlDataSource;
    }
}
