package com.oop.groupeighteen.group18cateringservice.rahul;

import com.oop.groupeighteen.group18cateringservice.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PlanDailyMealProductioncontroller
{
    @javafx.fxml.FXML
    private ListView kitchenteamLV;
    @javafx.fxml.FXML
    private TextField passergerCountTF;
    @javafx.fxml.FXML
    private ComboBox airlineCB;
    @javafx.fxml.FXML
    private DatePicker plandailymealDP;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void plandailymealOnActionDP(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void airlineOnActionCB(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void generatepreplistAB(ActionEvent actionEvent) {
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