/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createpropertiesfile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salma
 */
public class CreatePropertiesFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Properties properties = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("propertiesFile.properties");
            properties.setProperty("URL", "jdbc:mysql://localhost:3306/employeeDB");
            properties.setProperty("USERNAME", "root");
            properties.setProperty("PASSWORD", "salma");
            properties.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
