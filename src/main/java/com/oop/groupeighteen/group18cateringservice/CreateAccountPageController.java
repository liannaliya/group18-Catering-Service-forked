package com.oop.groupeighteen.group18cateringservice;

import com.oop.groupeighteen.group18cateringservice.rahul.Airline;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.time.LocalDate;

public class CreateAccountPageController
{
    @javafx.fxml.FXML
    private TextArea outputTA;
    @javafx.fxml.FXML
    private TextField phoneTF;
    @javafx.fxml.FXML
    private TextArea addressTA;
    @javafx.fxml.FXML
    private RadioButton othersRB;
    @javafx.fxml.FXML
    private TextField nameTF;
    @javafx.fxml.FXML
    private TextField emailTF;
    @javafx.fxml.FXML
    private RadioButton maleRB;
    @javafx.fxml.FXML
    private DatePicker dobDP;
    @javafx.fxml.FXML
    private RadioButton femaleRB;
    @javafx.fxml.FXML
    private PasswordField passeordPF;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void createaAccountOA(ActionEvent actionEvent) {
        String name, email,phone,gender = "",address,password;
        LocalDate dob;

        name = nameTF.getText();
        email = emailTF.getText();
        phone = phoneTF.getText();
        address = addressTA.getText();
        password = passeordPF.getText();
        dob = dobDP.getValue();

        if (maleRB.isSelected()){
            gender = "Male";
        }
        else if (femaleRB.isSelected()){
            gender = "Female";
        }
        else if (othersRB.isSelected()){
            gender = "Others";
        }

        Airline airline = new Airline(name,phone,email,address,gender,password,dob);

        outputTA.clear();
        outputTA.setText(airline.toString());



        nameTF.clear();
        emailTF.clear();
        phoneTF.clear();
        addressTA.clear();
        passeordPF.clear();


    }
}