package com.oop.groupeighteen.group18cateringservice.Lianna;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HeadChefDashboardcontroller {

    @FXML
    private Button menuplanbutton;
    @FXML
    private Button assignedtaskbutton;
    @FXML
    private Button kitchenprogressbutton;
    @FXML
    private Button ingredientsinventorybutton;
    @FXML
    private Button flightupdatebutton;
    @FXML
    private Button specialmealbutton;
    @FXML
    private Button qualitycheckbutton;
    @FXML
    private Button reviewperformancebutton;

    @FXML
    public void initialize() {
    }

    @FXML
    private void menuplanbutton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HeadChef/MenuPlanDashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void assignedtaskbutton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HeadChef/AssignedTasksDashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void kitchenprogressbutton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HeadChef/KitchenProgressDashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ingredientsinventorbutton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HeadChef/Ingredients Inventory Dashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void flightupdatebutton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HeadChef/FlightUpdateDashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void specialmealbutton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HeadChef/SpecialMeal.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void qualitycheckbutton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HeadChef/QualityCheckDashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void reviewperformancebutton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HeadChef/ReviewPerformanceDashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logoutbutton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/oop/groupeighteen/group18cateringservice/Login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
