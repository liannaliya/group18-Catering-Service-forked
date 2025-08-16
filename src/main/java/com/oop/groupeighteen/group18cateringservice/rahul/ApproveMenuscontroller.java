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
import javafx.stage.Stage;

import java.io.IOException;

public class ApproveMenuscontroller
{
    @javafx.fxml.FXML
    private TableView approveTV;
    @javafx.fxml.FXML
    private TableColumn quantityCOL;
    @javafx.fxml.FXML
    private TableColumn menuItemCOL;
    @javafx.fxml.FXML
    private TextArea commentTA;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void approveAB(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void rejectAB(ActionEvent actionEvent) {
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