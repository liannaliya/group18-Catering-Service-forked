package com.oop.groupeighteen.group18cateringservice;

import com.oop.groupeighteen.group18cateringservice.rahul.Admin;
import com.oop.groupeighteen.group18cateringservice.rahul.Cateringmanager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Logincontroller
{
    @javafx.fxml.FXML
    private PasswordField passwordfield;
    @javafx.fxml.FXML
    private Label errorlabel;
    @javafx.fxml.FXML
    private TextField useridtf;
    Alert alert;
    ArrayList<Admin> adminArrayList = new ArrayList<>();
    ArrayList<Cateringmanager> cateringmanagerArrayList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        Admin admin = new Admin("Admin", "0163678945", "abc@gmail.com", "Dhaka", "Male", "1234", LocalDate.of(1995, 3, 10)) {
            @Override
            public String genarateID() {
                return "a";
            }
        };
        adminArrayList.add(admin);

        Cateringmanager cateringmanager = new Cateringmanager("Catering Manager", "0163670945", "abcd@gmail.com", "Dhaka", "Male", "12345", LocalDate.of(1995, 3, 10)) {
            @Override
            public String genarateID() {
                return "cm";
            }
        };
        cateringmanagerArrayList.add(cateringmanager);
    }


    @javafx.fxml.FXML
    public void loginButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Login Page");
        String id , password;
        boolean flag = true;

        Alert erroralert= new Alert(Alert.AlertType.ERROR);

        id = useridtf.getText();
        password = passwordfield.getText();

        if (id.isBlank()){
            flag = false;
            erroralert.setTitle("User ID ERROR");
            erroralert.setTitle("User ID can not be blank");
            erroralert.showAndWait();
        }

        if (password.isBlank()){
            flag = false;
            erroralert.setTitle("Password ID ERROR");
            erroralert.setTitle("Password can not be blank");
            erroralert.showAndWait();
        }

        if (flag) {
            if (id.length() == 1) {
                for (Admin admin : adminArrayList) {
                    if (admin.login(id, password)) {
                        Parent root = null ;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rahul/AdminDashboard.fxml"));
                        root = fxmlLoader.load();
                        Scene scene = new Scene(root) ;
                        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Admin Dashboard");
                        stage.show();


                    }
                }
                //login as a admin

            } else if (id.length() == 2) {
                for (Cateringmanager cateringmanager : cateringmanagerArrayList) {
                    if (cateringmanager.login(id, password)) {
                        Parent root = null;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rahul/CateringManagerDashboard.fxml"));
                        root = fxmlLoader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Catering Manager Dashboard");
                        stage.show();
                    }
                }
                //login as a Catering Manager

            } else {
                erroralert.setTitle("User ID ERROR");
                erroralert.setTitle("User ID Does not exit");
                erroralert.showAndWait();
            }
        }


    }

    @javafx.fxml.FXML
    public void createaccountButton(ActionEvent actionEvent) {
    }
}