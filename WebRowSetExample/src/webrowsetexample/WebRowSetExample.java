/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webrowsetexample;

import com.sun.rowset.WebRowSetImpl;
import java.io.FileWriter;
import javax.sql.rowset.WebRowSet;

/**
 *
 * @author salma
 */
public class WebRowSetExample {

    WebRowSet employeesList;

    public WebRowSetExample() {
        try {

            employeesList = new WebRowSetImpl();
            employeesList.setUsername("root");
            employeesList.setPassword("salma");
            employeesList.setUrl("jdbc:mysql://localhost:3306/employeeDB");
            employeesList.setCommand("select * from Employee");
            employeesList.execute();
            FileWriter writer = new FileWriter("emplist.xml");
            employeesList.writeXml(writer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new WebRowSetExample();
    }

}
