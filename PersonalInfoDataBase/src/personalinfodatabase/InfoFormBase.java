package personalinfodatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.sql.DataSource;

public class InfoFormBase extends Pane {

    DataSource dataSource = null;
    Connection connection = null;
    Statement statment = null;
    ResultSet resultSet = null;
    

    int IDValue;
    String FNameValue;
    String LNameValue;
    String MidNameValue;
    String EmailValue;
    String PhoneValue;

    boolean newPerson;

    protected final Label ID;
    protected final Label FName;
    protected final Label MidName;
    protected final Label LName;
    protected final Label Email;
    protected final Label PhoneNum;
    protected final TextField IDField;
    protected final TextField FNameField;
    protected final TextField MidNameField;
    protected final TextField LNameField;
    protected final TextField EmailField;
    protected final TextField PhoneField;
    protected final Button New;
    protected final Button Update;
    protected final Button Delete;
    protected final Button First;
    protected final Button Previous;
    protected final Button Next;
    protected final Button Last;

    public InfoFormBase(Stage stage) {

        ID = new Label();
        FName = new Label();
        MidName = new Label();
        LName = new Label();
        Email = new Label();
        PhoneNum = new Label();
        IDField = new TextField();
        FNameField = new TextField();
        MidNameField = new TextField();
        LNameField = new TextField();
        EmailField = new TextField();
        PhoneField = new TextField();
        New = new Button();
        Update = new Button();
        Delete = new Button();
        First = new Button();
        Previous = new Button();
        Next = new Button();
        Last = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        ID.setLayoutX(58.0);
        ID.setLayoutY(39.0);
        ID.setText("ID:");

        FName.setLayoutX(58.0);
        FName.setLayoutY(83.0);
        FName.setText("First Name:");

        MidName.setLayoutX(58.0);
        MidName.setLayoutY(126.0);
        MidName.setText("Middle Name:");

        LName.setLayoutX(58.0);
        LName.setLayoutY(168.0);
        LName.setText("Last Name:");

        Email.setLayoutX(58.0);
        Email.setLayoutY(215.0);
        Email.setText("Email:");

        PhoneNum.setLayoutX(58.0);
        PhoneNum.setLayoutY(261.0);
        PhoneNum.setText("Phone:");

        IDField.setLayoutX(207.0);
        IDField.setLayoutY(34.0);
        IDField.setDisable(true);

        FNameField.setLayoutX(207.0);
        FNameField.setLayoutY(78.0);

        MidNameField.setLayoutX(207.0);
        MidNameField.setLayoutY(121.0);

        LNameField.setLayoutX(207.0);
        LNameField.setLayoutY(163.0);

        EmailField.setLayoutX(207.0);
        EmailField.setLayoutY(210.0);
        EmailField.setPrefWidth(300);

        PhoneField.setLayoutX(207.0);
        PhoneField.setLayoutY(256.0);

        New.setLayoutX(42.0);
        New.setLayoutY(333.0);
        New.setMnemonicParsing(false);
        New.setText("New");

        Update.setLayoutX(105.0);
        Update.setLayoutY(333.0);
        Update.setMnemonicParsing(false);
        Update.setPrefHeight(31.0);
        Update.setPrefWidth(76.0);
        Update.setText("Update");

        Delete.setLayoutX(191.0);
        Delete.setLayoutY(333.0);
        Delete.setMnemonicParsing(false);
        Delete.setPrefHeight(31.0);
        Delete.setPrefWidth(69.0);
        Delete.setText("Delete");

        First.setLayoutX(271.0);
        First.setLayoutY(333.0);
        First.setMnemonicParsing(false);
        First.setPrefHeight(31.0);
        First.setPrefWidth(63.0);
        First.setText("First");

        Previous.setLayoutX(343.0);
        Previous.setLayoutY(333.0);
        Previous.setMnemonicParsing(false);
        Previous.setText("Previous");

        Next.setLayoutX(429.0);
        Next.setLayoutY(333.0);
        Next.setMnemonicParsing(false);
        Next.setText("Next");

        Last.setLayoutX(490.0);
        Last.setLayoutY(333.0);
        Last.setMnemonicParsing(false);
        Last.setText("Last");

        getChildren().add(ID);
        getChildren().add(FName);
        getChildren().add(MidName);
        getChildren().add(LName);
        getChildren().add(Email);
        getChildren().add(PhoneNum);
        getChildren().add(IDField);
        getChildren().add(FNameField);
        getChildren().add(MidNameField);
        getChildren().add(LNameField);
        getChildren().add(EmailField);
        getChildren().add(PhoneField);
        getChildren().add(New);
        getChildren().add(Update);
        getChildren().add(Delete);
        getChildren().add(First);
        getChildren().add(Previous);
        getChildren().add(Next);
        getChildren().add(Last);
        connect();
        

        First.setOnAction((ActionEvent e) -> {
            try {
                resultSet.first();
                IDValue = resultSet.getInt(1);
                IDField.setText(Integer.toString(IDValue));
                FNameValue = resultSet.getString(2);
                FNameField.setText(FNameValue);
                MidNameValue = resultSet.getString(3);
                MidNameField.setText(MidNameValue);
                LNameValue = resultSet.getString(4);
                LNameField.setText(LNameValue);
                EmailValue = resultSet.getString(5);
                EmailField.setText(EmailValue);
                PhoneValue = resultSet.getString(6);
                PhoneField.setText(PhoneValue);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Last.setOnAction((ActionEvent e) -> {
            try {
                resultSet.last();
                IDValue = resultSet.getInt(1);
                IDField.setText(Integer.toString(IDValue));
                FNameValue = resultSet.getString(2);
                FNameField.setText(FNameValue);
                MidNameValue = resultSet.getString(3);
                MidNameField.setText(MidNameValue);
                LNameValue = resultSet.getString(4);
                LNameField.setText(LNameValue);
                EmailValue = resultSet.getString(5);
                EmailField.setText(EmailValue);
                PhoneValue = resultSet.getString(6);
                PhoneField.setText(PhoneValue);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Next.setOnAction((ActionEvent e) -> {
            try {
                if (resultSet.isLast()) {
                    resultSet.beforeFirst();
                }
                resultSet.next();
                IDValue = resultSet.getInt(1);
                IDField.setText(Integer.toString(IDValue));
                FNameValue = resultSet.getString(2);
                FNameField.setText(FNameValue);
                MidNameValue = resultSet.getString(3);
                MidNameField.setText(MidNameValue);
                LNameValue = resultSet.getString(4);
                LNameField.setText(LNameValue);
                EmailValue = resultSet.getString(5);
                EmailField.setText(EmailValue);
                PhoneValue = resultSet.getString(6);
                PhoneField.setText(PhoneValue);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Previous.setOnAction((ActionEvent e) -> {
            try {
                if (resultSet.isFirst()) {
                    resultSet.afterLast();
                }
                resultSet.previous();
                IDValue = resultSet.getInt(1);
                IDField.setText(Integer.toString(IDValue));
                FNameValue = resultSet.getString(2);
                FNameField.setText(FNameValue);
                MidNameValue = resultSet.getString(3);
                MidNameField.setText(MidNameValue);
                LNameValue = resultSet.getString(4);
                LNameField.setText(LNameValue);
                EmailValue = resultSet.getString(5);
                EmailField.setText(EmailValue);
                PhoneValue = resultSet.getString(6);
                PhoneField.setText(PhoneValue);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Delete.setOnAction((ActionEvent e) -> {
            try {

                    resultSet.deleteRow();
                    clearFields();
                    
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        New.setOnAction((ActionEvent e) -> {
            try {
                //clearing fields
                clearFields();
                //Setting flag   
                newPerson = true;
                //go to new row
                resultSet.moveToInsertRow();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Update.setOnAction((ActionEvent e) -> {

            try {
                if (newPerson) {
                    if (FNameField.getText().isEmpty() || MidNameField.getText().isEmpty() || LNameField.getText().isEmpty() || EmailField.getText().isEmpty() || PhoneField.getText().isEmpty()) {
                        fillFieldsErrorMsg();
                    } else {
 
                        resultSet.updateInt(1, IDValue);
                        FNameValue = FNameField.getText();
                        resultSet.updateString(2, FNameValue);
                        MidNameValue = MidNameField.getText();
                        resultSet.updateString(3, MidNameValue);
                        LNameValue = LNameField.getText();
                        resultSet.updateString(4, LNameValue);
                        EmailValue = EmailField.getText();
                        resultSet.updateString(5, EmailValue);
                        PhoneValue = PhoneField.getText();
                        resultSet.updateString(6, PhoneValue);
                        resultSet.insertRow();
                        newPerson = false;
                        clearFields();
                        

                    }
                } else {
                    if (FNameField.getText().isEmpty() || MidNameField.getText().isEmpty() || LNameField.getText().isEmpty() || EmailField.getText().isEmpty() || PhoneField.getText().isEmpty()) {
                        fillFieldsErrorMsg();
                    } else {
                        
                        FNameValue = FNameField.getText();
                        resultSet.updateString(2, FNameValue);
                        MidNameValue = MidNameField.getText();
                        resultSet.updateString(3, MidNameValue);
                        LNameValue = LNameField.getText();
                        resultSet.updateString(4, LNameValue);
                        EmailValue = EmailField.getText();
                        resultSet.updateString(5, EmailValue);
                        PhoneValue = PhoneField.getText();
                        resultSet.updateString(6, PhoneValue);
                        resultSet.updateRow();
                        clearFields();
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        );

        stage.setOnCloseRequest(
                new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
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
        );

    }


    public void fillFieldsErrorMsg() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Empty Fields");
        alert.setHeaderText("Error");
        alert.setContentText("Insert values Into all Fields Please");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }

    public void clearFields() {
        IDField.clear();
        FNameField.clear();
        MidNameField.clear();
        LNameField.clear();
        EmailField.clear();
        PhoneField.clear();
    }

   

    public void connect() {

        try {
            dataSource = DataSourceFactory.getMySQLDataSource();
            connection = dataSource.getConnection();
            statment = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statment.executeQuery("select * from PersonalInfo");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
