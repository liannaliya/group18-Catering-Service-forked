package com.oop.groupeighteen.group18cateringservice.rahul;

import com.oop.groupeighteen.group18cateringservice.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CateringManagerDashboardcontroller
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void dailyMealPlannerAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/planDailyMealProduction.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void inventoryManagerAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/maintainInventoryIngredients.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void myAirlinesAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/viewAssignedAirlines.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void shiftManagementAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/manageStaffShifts.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void airlineFeedbackAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/reviewFeedbackAirline.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void dailyReportAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/submitEndofDayReport.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void mealTrackerAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/trackMealPreparation.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void menuManagementAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/createProposeMenus.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void backAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.show();
    }
}