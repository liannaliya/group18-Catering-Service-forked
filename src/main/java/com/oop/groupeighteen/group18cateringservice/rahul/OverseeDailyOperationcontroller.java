package com.oop.groupeighteen.group18cateringservice.rahul;

import com.oop.groupeighteen.group18cateringservice.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class OverseeDailyOperationcontroller
{
    @javafx.fxml.FXML
    private TableColumn mealsCOL;
    @javafx.fxml.FXML
    private DatePicker overseeDP;
    @javafx.fxml.FXML
    private TableColumn preaparedCOL;
    @javafx.fxml.FXML
    private TableColumn airlineCOL;
    @javafx.fxml.FXML
    private TableColumn delivartedCOL;
    @javafx.fxml.FXML
    private TableView overseeoperationTV;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void downloadAB(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void overseeOnAactionDP(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void filterAB(ActionEvent actionEvent) {
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