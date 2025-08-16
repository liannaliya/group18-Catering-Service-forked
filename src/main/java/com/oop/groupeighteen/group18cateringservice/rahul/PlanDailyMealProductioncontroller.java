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
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PlanDailyMealProductioncontroller {
    @javafx.fxml.FXML
    private ListView<String> kitchenteamLV;
    @javafx.fxml.FXML
    private TextField passergerCountTF;
    @javafx.fxml.FXML
    private ComboBox<String> airlineCB;
    @javafx.fxml.FXML
    private DatePicker plandailymealDP;

    private ObservableList<String> kitchenStaff = FXCollections.observableArrayList("A", "B", "C", "D", "E");

    private ObservableList<String> airlines = FXCollections.observableArrayList("1", "2", "3", "4");

    private Map<String, String> mealRequirements = new HashMap<>();

    @javafx.fxml.FXML
    public void initialize() {
        kitchenteamLV.setItems(kitchenStaff);
        airlineCB.setItems(airlines);

        plandailymealDP.setValue(LocalDate.now());


        mealRequirements.put("a", "300 , 50 , 20 ");
        mealRequirements.put("b", "250 , 40 , 30 ");
        mealRequirements.put("c", "400 , 60 , 40 ");
        mealRequirements.put("d", "350 , 30 , 15 ");
    }

    @javafx.fxml.FXML
    public void plandailymealOnActionDP(ActionEvent actionEvent) {
        LocalDate selectedDate = plandailymealDP.getValue();
    }

    @javafx.fxml.FXML
    public void airlineOnActionCB(ActionEvent actionEvent) {
        String selectedAirline = airlineCB.getValue();
        if (selectedAirline != null && mealRequirements.containsKey(selectedAirline)) {
            passergerCountTF.setPromptText("Typical meals: " + mealRequirements.get(selectedAirline));
        }
    }

    @javafx.fxml.FXML
    public void generatepreplistAB(ActionEvent actionEvent) {
        if (airlineCB.getValue() == null || plandailymealDP.getValue() == null) {
            showAlert("Error", "Please select both date and airline");
            return;
        }

        try {
            int passengerCount = Integer.parseInt(passergerCountTF.getText());
            if (passengerCount <= 0) {
                showAlert("Error", "Passenger count must be positive");
                return;
            }

            ObservableList<String> selectedStaff = kitchenteamLV.getSelectionModel().getSelectedItems();
            if (selectedStaff.isEmpty()) {
                showAlert("Warning", "No kitchen staff selected for this shift");
            }

            StringBuilder prepList = new StringBuilder();
            prepList.append("Daily Meal Production Plan\n");
            prepList.append("Date: ").append(plandailymealDP.getValue()).append("\n");
            prepList.append("Airline: ").append(airlineCB.getValue()).append("\n");
            prepList.append("Passenger Count: ").append(passengerCount).append("\n");
            prepList.append("Assigned Team:\n");

            for (String staff : selectedStaff) {
                prepList.append("- ").append(staff).append("\n");
            }

            prepList.append("\nRequired Resources:\n");
            prepList.append("- Standard Meals: ").append((int)(passengerCount * 0.8)).append("\n");
            prepList.append("- Special Meals: ").append((int)(passengerCount * 0.2)).append("\n");
            prepList.append("- Beverages: ").append(passengerCount * 2).append("\n");

            showAlert("Daily Prep List", prepList.toString());

        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for passenger count");
        }
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