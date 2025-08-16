package com.oop.groupeighteen.group18cateringservice.rahul;

import com.oop.groupeighteen.group18cateringservice.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class AssignCateringManagerscontroller
{
    @javafx.fxml.FXML
    private ListView<String> managersLV;
    @javafx.fxml.FXML
    private ComboBox<String> airlineNameCB;
    private ObservableList<String> managers = FXCollections.observableArrayList("A", "B", "C", "D");

    private ObservableList<String> airlines = FXCollections.observableArrayList("US Bangla Airlines", "Qater Airlines", "Emirat Airlines", "Boing Airlines");

    @javafx.fxml.FXML
    public void initialize() {
        managersLV.setItems(managers);
        airlineNameCB.setItems(airlines);

    }

    @javafx.fxml.FXML
    public void assignAB(ActionEvent actionEvent) {
        String selectedManager = managersLV.getSelectionModel().getSelectedItem();
        String selectedAirline = airlineNameCB.getValue();

        if (selectedManager == null || selectedAirline == null) {
            showAlert("Selection Error", "Please select both a manager and an airline");
            return;
        }
        showAlert("Assignment Successful",
                String.format("Assigned %s to %s", selectedManager, selectedAirline));
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void backAB(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/AdminDashboard.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Admin Dashboard");
        stage.show();
    }
}