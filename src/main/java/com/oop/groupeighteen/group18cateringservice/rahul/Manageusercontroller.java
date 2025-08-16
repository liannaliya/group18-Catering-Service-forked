package com.oop.groupeighteen.group18cateringservice.rahul;

import com.oop.groupeighteen.group18cateringservice.HelloApplication;
import com.oop.groupeighteen.group18cateringservice.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class Manageusercontroller
{
    @FXML
    private TableView<Newuser> manageaccTV;
    @FXML
    private TextField usernameTF;
    @FXML
    private TableColumn<Newuser,String> statusCOL;
    @FXML
    private TableColumn<Newuser,String> uernameCOL;
    @FXML
    private TableColumn<Newuser,String> roleCOL;

    ObservableList<Newuser> newusers = FXCollections.observableArrayList() ;
    private TableView<Newuser> tableView;


    @FXML
    private TextField phoneTF;
    @FXML
    private TextField emailTF;



    @FXML
    public void initialize() {
        uernameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleCOL.setCellValueFactory(new PropertyValueFactory<>("phone"));
        statusCOL.setCellValueFactory(new PropertyValueFactory<>("email"));
        ListView<Newuser> tableView = new ListView<>();
        tableView.setItems(newusers);

    }

    @FXML
    public void saveAB(ActionEvent actionEvent) {
        UserManager.saveUserList(tableView.getItems());
    }


    @FXML
    public void editAB(ActionEvent actionEvent) {
        Newuser s = tableView.getSelectionModel().getSelectedItem();
        if (s != null) {
            String name = usernameTF.getText();
            String phone = phoneTF.getText();
            String email = emailTF.getText();

            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                s.setName(name);
                s.setPhone(phone);
                s.setEmail(email);

                tableView.refresh();

                usernameTF.clear();
                phoneTF.clear();
                emailTF.clear();
            } else {
                showAlert("Error", "All fields must be filled!");
            }
        } else {
            showAlert("Error", "No user selected!");
        }

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();


    }

    @FXML
    public void addAB(ActionEvent actionEvent) {
        String name = usernameTF.getText();
        String phone = phoneTF.getText();
        String email = emailTF.getText();

        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            newusers.add(new Newuser(name, phone, email));
            usernameTF.clear();
            phoneTF.clear();
            emailTF.clear();
        }



    }

    @FXML
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