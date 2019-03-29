/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalinfodatabase;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author salma
 */
public class PersonalInfoDataBase extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        InfoFormBase root=new InfoFormBase(primaryStage);
        Scene scene = new Scene(root, 575, 400);
        
        primaryStage.setTitle("Personal Info");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
