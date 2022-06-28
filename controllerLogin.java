package com.example.ctaaoop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class controllerLogin {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    String URL = "jdbc:mysql://localhost/ctaaoop";
    @FXML
    public void onSubmitBtnClk(){
        int flag=0;
        String name = username.getText();
        String passwd = password.getText();

        if(name.isEmpty() || passwd.isEmpty()){
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setHeaderText("Please enter username and password !");
            a1.showAndWait();
        }
        else{
            try{
                Connection connection = DriverManager.getConnection(URL,"root","Mattu@2001");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select username,password from login");
                while (resultSet.next()){
                    if(resultSet.getString(1).equals(name) && resultSet.getString(2).equals(passwd)) {
                        flag = 1;
                        break;
                    }
                }

                if(flag == 1){
                    try {
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(controllerLogin.class.getResource("ctaAoop.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        stage.setTitle("CIE");
                        stage.setScene(scene);
                        stage.show();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else {
                    Alert a2 = new Alert(Alert.AlertType.WARNING);
                    a2.setHeaderText("Invalid User or password");
                    a2.show();
                    username.setText(null);
                    password.setText(null);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onResetBtnClk(){
        username.setText(null);
        password.setText(null);
    }
}
