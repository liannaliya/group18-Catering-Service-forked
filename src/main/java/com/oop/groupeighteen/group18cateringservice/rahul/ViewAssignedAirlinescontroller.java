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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewAssignedAirlinescontroller {
    @javafx.fxml.FXML
    private TableColumn<Airline,String> airlineNameCOL;
    @javafx.fxml.FXML
    private TableView <Airline> viewassignairlineTV;
    @javafx.fxml.FXML
    private TableColumn <Airline,String>contactCOL;
    @javafx.fxml.FXML
    private TableColumn <Airline,String> servicedaysCOL;

    private ObservableList<Airline> assignedAirlines = FXCollections.observableArrayList();


    @javafx.fxml.FXML
    public void initialize() {
        airlineNameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactCOL.setCellValueFactory(new PropertyValueFactory<>("contact"));
        servicedaysCOL.setCellValueFactory(new PropertyValueFactory<>("serviceDays"));
        viewassignairlineTV.setItems(assignedAirlines);
    }

    @javafx.fxml.FXML
    public void viewScheduleAB(ActionEvent actionEvent) {
        Airline selectedAirline = viewassignairlineTV.getSelectionModel().getSelectedItem();

        if (selectedAirline == null) {
            showAlert("Selection Required", "Please select an airline to view its schedule");
            return;
        }

        // In a real application, you would show the detailed schedule here
        showAlert("Flight Schedule",
                "Schedule for " + selectedAirline.getName() + ":\n" +
                        "• Monday: 8AM, 2PM, 7PM\n" +
                        "• Wednesday: 9AM, 5PM\n" +
                        "• Friday: 6AM, 12PM, 6PM");

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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rahul/CateringManagerDashboard.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Catering Manager Dashboard");
        stage.show();
    }
}