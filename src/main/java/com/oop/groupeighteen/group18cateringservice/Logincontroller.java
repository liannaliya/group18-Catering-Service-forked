package com.oop.groupeighteen.group18cateringservice;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Logincontroller
{
    @javafx.fxml.FXML
    private PasswordField passwordfield;
    @javafx.fxml.FXML
    private Label errorlabel;
    @javafx.fxml.FXML
    private TextField useridtf;
    Alert alert;

    @javafx.fxml.FXML
    public void initialize() {
    }


    @javafx.fxml.FXML
    public void loginButton(ActionEvent actionEvent) {
        System.out.println("Login Page");
        String id , password;
        boolean flag = true;

        Alert erroralert= new Alert(Alert.AlertType.ERROR);

        id = useridtf.getText();
        password = passwordfield.getText();

        if (id.isBlank()){
            flag = false;
            erroralert.setTitle("User ID ERROR");
            erroralert.setTitle("User ID can not be blank");
            erroralert.showAndWait();
        }

        if (password.isBlank()){
            flag = false;
            erroralert.setTitle("Password ID ERROR");
            erroralert.setTitle("Password can not be blank");
            erroralert.showAndWait();
        }

        if (flag) {
            if (id.length() == 1) {
                //login as a admin

            } else if (id.length() == 2) {
                //login as a Catering Manager

            } else {
                erroralert.setTitle("User ID ERROR");
                erroralert.setTitle("User ID Does not exit");
                erroralert.showAndWait();
            }
        }


    }

    @javafx.fxml.FXML
    public void createaccountButton(ActionEvent actionEvent) {
    }
}