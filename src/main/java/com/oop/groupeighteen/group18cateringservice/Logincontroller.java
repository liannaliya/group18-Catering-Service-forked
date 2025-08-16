package com.oop.groupeighteen.group18cateringservice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Logincontroller {

    @FXML
    private TextField useridtf;
    
    @FXML
    private PasswordField passwordfield;
    
    @FXML
    private Label errorlabel;

    @FXML
    public void loginButton(ActionEvent actionEvent) throws IOException {
        String username = useridtf.getText();
        String password = passwordfield.getText();

        if (username.equals("Head Chef") && password.equals("Liya")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Lianna/HeadChef/HeadChefDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) useridtf.getScene().getWindow();
            stage.setTitle("Head Chef Dashboard");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (username.equals("Kitchen Staff") && password.equals("Liya")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Lianna/KitchenStaff/KitchenStaffDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) useridtf.getScene().getWindow();
            stage.setTitle("Kitchen Staff Dashboard");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (username.equals("Admin") && password.equals("Rahul")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rahul/AdminDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) useridtf.getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (username.equals("Catering Manager") && password.equals("Rahul")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rahul/CateringManagerDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) useridtf.getScene().getWindow();
            stage.setTitle("Catering Manager Dashboard");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (username.equals("Finance Officer") && password.equals("Maliha")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Maliha/Add Invoice.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) useridtf.getScene().getWindow();
            stage.setTitle("Finance Officer Dashboard");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (username.equals("Vehicle Manager") && password.equals("Maliha")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Maliha/VehicleManager.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) useridtf.getScene().getWindow();
            stage.setTitle("Vehicle Manager Dashboard");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            errorlabel.setText("Invalid Username or Password. Please try again.");
        }
    }

    @FXML
    public void createaccountButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccountPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) useridtf.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}