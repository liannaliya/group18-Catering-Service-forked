package com.oop.groupeighteen.group18cateringservice.rahul;

import com.oop.groupeighteen.group18cateringservice.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class TrackMealPreparationcontroller
{
    @javafx.fxml.FXML
    private TableColumn flightCOL;
    @javafx.fxml.FXML
    private TableColumn statusCOL;
    @javafx.fxml.FXML
    private TableView trackmealTV;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void updatstatusAB(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void markcompleteAB(ActionEvent actionEvent) {
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