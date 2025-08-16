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

public class CreateProposeMenuscontroller {
    @javafx.fxml.FXML
    private TableView<MenuItem> createproposeTV;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> typeCOL;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> ingredientsCOL;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, String> nameCOL;
    @javafx.fxml.FXML
    private ComboBox<String> dietaryCB;
    @javafx.fxml.FXML
    private TableColumn<MenuItem, Double> priceCOL;

    private ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
    private ObservableList<String> dietaryOptions = FXCollections.observableArrayList("Vegetarian", "Vegan", "Gluten-Free", "Halal", "Dairy-Free");

    @javafx.fxml.FXML
    public void initialize() {
        nameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeCOL.setCellValueFactory(new PropertyValueFactory<>("type"));
        ingredientsCOL.setCellValueFactory(new PropertyValueFactory<>("ingredients"));
        priceCOL.setCellValueFactory(new PropertyValueFactory<>("price"));

        dietaryCB.setItems(dietaryOptions);

        menuItems.add(new MenuItem("Chicken ", "Main", "Chicken, cream", 1200, ""));
        menuItems.add(new MenuItem("Galad", "Starter", "Apple", 800, "Vegetarian"));

        createproposeTV.setItems(menuItems);
    }

    @javafx.fxml.FXML
    public void additemAB(ActionEvent actionEvent) {

        String name = "New Item";
        String type = "Main";
        String ingredients = "Ingredients";
        double price = 960;
        String dietary = dietaryCB.getValue();

        MenuItem newItem = new MenuItem(name, type, ingredients, price, dietary);
        menuItems.add(newItem);

        showAlert("Success", "Menu item added successfully!");
    }

    @javafx.fxml.FXML
    public void dietaryOnActionCB(ActionEvent actionEvent) {
        String selectedDietary = dietaryCB.getValue();
    }

    @javafx.fxml.FXML
    public void submitdorapprovalAB(ActionEvent actionEvent) {
        if (menuItems.isEmpty()) {
            showAlert("Error", "Cannot submit empty menu");
            return;
        }
        showAlert("Submitted", "Menu submitted for admin approval successfully!");
        menuItems.clear();
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}