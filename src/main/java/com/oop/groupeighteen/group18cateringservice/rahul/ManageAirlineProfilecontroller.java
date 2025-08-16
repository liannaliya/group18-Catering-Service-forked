package com.oop.groupeighteen.group18cateringservice.rahul;

import com.oop.groupeighteen.group18cateringservice.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageAirlineProfilecontroller
{
    @javafx.fxml.FXML
    private TableColumn<Airline,String> airlineNameCOL;
    @javafx.fxml.FXML
    private TableView <Airline> manageAirlineTV;
    @javafx.fxml.FXML
    private TableColumn <Airline,String> contactCOL;
    @javafx.fxml.FXML
    private TextField airlineNameTF;
    @javafx.fxml.FXML
    private TextArea contractTermsTA;
    @javafx.fxml.FXML
    private TextField contactTF;
    @javafx.fxml.FXML
    private TableColumn <Airline,String> contactTermsCOL;

    private ObservableList<Airline> airlines = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        airlineNameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactCOL.setCellValueFactory(new PropertyValueFactory<>("contact"));
        contactTermsCOL.setCellValueFactory(new PropertyValueFactory<>("terms"));


    }

    @javafx.fxml.FXML
    public void saveAB(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void editAB(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void addAB(ActionEvent actionEvent) {
        if (airlineNameTF.getText().isEmpty() || contactTF.getText().isEmpty()) {
            showAlert("Error", "Name and contact are required");
            return;
        }


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