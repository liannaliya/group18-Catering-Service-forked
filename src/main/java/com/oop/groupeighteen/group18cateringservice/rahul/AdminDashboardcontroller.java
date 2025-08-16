package com.oop.groupeighteen.group18cateringservice.rahul;

import com.oop.groupeighteen.group18cateringservice.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardcontroller
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void systemlogsAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/generateAuditLogs.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void pendingmenusAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/approveMenus.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void feedbackpanelAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/ manageFeedbackComplaints.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void assignrolesAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/assignCateringManagers.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void rolepermissinAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/controlAccessPermissions.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void userManagementAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/manageuser.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void dailyrepotsAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/overseeDailyOperation.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("hello");
        stage.show();
    }

    @javafx.fxml.FXML
    public void airlinesettingsAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/manageAirlineProfile.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("hello");
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
        stage.setTitle("Admin Dashboard");
        stage.show();
    }
}