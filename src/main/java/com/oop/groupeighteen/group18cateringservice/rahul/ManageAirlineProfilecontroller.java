package com.oop.groupeighteen.group18cateringservice.rahul;

import com.oop.groupeighteen.group18cateringservice.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageAirlineProfilecontroller
{
    @javafx.fxml.FXML
    private TableColumn airlineNameCOL;
    @javafx.fxml.FXML
    private TableView manageAirlineTV;
    @javafx.fxml.FXML
    private TableColumn contactCOL;
    @javafx.fxml.FXML
    private TextField airlineNameTF;
    @javafx.fxml.FXML
    private TextArea contractTermsTA;
    @javafx.fxml.FXML
    private TextField contactTF;
    @javafx.fxml.FXML
    private TableColumn contactTermsCOL;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void saveAB(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void editAB(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void addAB(ActionEvent actionEvent) {
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