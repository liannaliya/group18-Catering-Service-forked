package com.oop.groupeighteen.group18cateringservice.Lianna;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import com.oop.groupeighteen.group18cateringservice.Lianna.models.MenuPlan;

public class MenuPlanDashboardController {
    
    @FXML
    private TableView<MenuPlan> viewlistofmealtableview;
    
    @FXML
    private TableColumn<MenuPlan, String> flightcol;
    
    @FXML
    private TableColumn<MenuPlan, String> airlinecol;
    
    @FXML
    private TableColumn<MenuPlan, String> datecol;
    
    @FXML
    private TableColumn<MenuPlan, String> mealtypecol;
    
    @FXML
    private TableColumn<MenuPlan, String> mealnamecol;
    
    @FXML
    private TableColumn<MenuPlan, String> specialrequestscol;
    
    @FXML
    private TableColumn<MenuPlan, Integer> passengercountcol;
    
    @FXML
    private TableColumn<MenuPlan, String> statuscol;
    
    @FXML
    private TextField flightNumberTextField;
    
    @FXML
    private TextField airlinenamemenutextfield;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private ComboBox<String> mealTypeComboBox;
    
    @FXML
    private TextField mealnamemenutextfield;
    
    @FXML
    private TextArea mealDescriptionTextArea;
    
    @FXML
    private TextField passengerCountTextField;
    
    @FXML
    private ComboBox<String> mealCategoryComboBox;
    
    @FXML
    private TextField costPerMealTextField;
    
    @FXML
    private ComboBox<String> statusComboBox;
    
    @FXML
    private CheckBox specialMealCheckBox;
    
    @FXML
    private TextArea specialrequestareamenu;
    
    @FXML
    private TextArea dietaryRestrictionsTextArea;
    
    @FXML
    private TextArea notesTextArea;
    
    @FXML
    private ComboBox<String> filterSpecialRequestsComboBox;
    
    private ObservableList<MenuPlan> menuPlansList = FXCollections.observableArrayList();
    private MenuPlan selectedPlan;
    private static final String MENU_PLANS_FILE = "menu_plans.bin";
    
    @FXML
    public void initialize() {
        setupTableColumns();
        setupComboBoxes();
        setupSpinner();
        loadMenuPlans();
        setupTableSelection();
    }
    
    private void setupTableColumns() {
        flightcol.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        airlinecol.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("flightDate"));
        mealtypecol.setCellValueFactory(new PropertyValueFactory<>("mealType"));
        mealnamecol.setCellValueFactory(new PropertyValueFactory<>("mealName"));
        specialrequestscol.setCellValueFactory(new PropertyValueFactory<>("status"));
        passengercountcol.setCellValueFactory(new PropertyValueFactory<>("passengerCount"));
        statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    
    private void setupComboBoxes() {
        mealTypeComboBox.getItems().addAll("Breakfast", "Lunch", "Dinner", "Snack");
        mealCategoryComboBox.getItems().addAll("Economy", "Business", "First Class");
        statusComboBox.getItems().addAll("Pending", "In Progress", "Completed", "Cancelled");
        filterSpecialRequestsComboBox.getItems().addAll("All", "Vegetarian", "Vegan", "Gluten-Free", "Halal", "Kosher");
    }
    
    private void setupSpinner() {
    }
    
    private void loadMenuPlans() {
        menuPlansList.clear();
        File file = new File(MENU_PLANS_FILE);
        
        if (!file.exists()) {
            createEmptyFile();
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof MenuPlan) {
                        MenuPlan plan = (MenuPlan) obj;
                        menuPlansList.add(plan);
                    } else if (obj instanceof ArrayList) {
                        ArrayList<MenuPlan> plans = (ArrayList<MenuPlan>) obj;
                        menuPlansList.addAll(plans);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        viewlistofmealtableview.setItems(menuPlansList);
    }
    
    private void setupTableSelection() {
        viewlistofmealtableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedPlan = newSelection;
                loadPlanToForm(newSelection);
            }
        });
    }
    
    private void loadPlanToForm(MenuPlan plan) {
        flightNumberTextField.setText(plan.getFlightNumber());
        mealTypeComboBox.setValue(plan.getMealType());
        mealnamemenutextfield.setText(plan.getMealName());
        passengerCountTextField.setText(String.valueOf(plan.getPassengerCount()));
        datePicker.setValue(plan.getFlightDate());
        statusComboBox.setValue(plan.getStatus());
    }
    
    @FXML
    public void savemenubutton(ActionEvent actionEvent) {
        if (flightNumberTextField.getText().isEmpty() || mealTypeComboBox.getValue() == null ||
            mealnamemenutextfield.getText().isEmpty() || datePicker.getValue() == null) {
            showAlert("Error", "Please fill in all fields");
            return;
        }
        
        try {
            int passengerCount = Integer.parseInt(passengerCountTextField.getText());
            
            MenuPlan newPlan = new MenuPlan(
                "PLAN" + System.currentTimeMillis(),
                flightNumberTextField.getText(),
                mealTypeComboBox.getValue(),
                mealnamemenutextfield.getText(),
                passengerCount,
                statusComboBox.getValue() != null ? statusComboBox.getValue() : "Pending",
                datePicker.getValue()
            );
            
            menuPlansList.add(newPlan);
            saveToBinaryFile(newPlan);
            clearForm();
            showAlert("Success", "Menu plan saved successfully!");
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for passenger count");
        }
    }
    
    @FXML
    public void updateMenuButton(ActionEvent actionEvent) {
        if (selectedPlan == null) {
            showAlert("Error", "Please select a menu plan to update");
            return;
        }
        
        try {
            int passengerCount = Integer.parseInt(passengerCountTextField.getText());
            
            selectedPlan.setFlightNumber(flightNumberTextField.getText());
            selectedPlan.setMealType(mealTypeComboBox.getValue());
            selectedPlan.setMealName(mealnamemenutextfield.getText());
            selectedPlan.setPassengerCount(passengerCount);
            selectedPlan.setFlightDate(datePicker.getValue());
            selectedPlan.setStatus(statusComboBox.getValue());
            
            viewlistofmealtableview.refresh();
            saveAllPlans();
            clearForm();
            showAlert("Success", "Menu plan updated successfully!");
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for passenger count");
        }
    }
    
    @FXML
    public void viewMealRequirementsButton(ActionEvent actionEvent) {
        if (menuPlansList.isEmpty()) {
            showAlert("Information", "No meal plans available");
            return;
        }
        
        StringBuilder requirements = new StringBuilder();
        requirements.append("MEAL REQUIREMENTS REPORT\n\n");
        
        for (MenuPlan plan : menuPlansList) {
            requirements.append("Flight: ").append(plan.getFlightNumber())
                      .append(" | Meal: ").append(plan.getMealName())
                      .append(" | Type: ").append(plan.getMealType())
                      .append(" | Passengers: ").append(plan.getPassengerCount())
                      .append(" | Status: ").append(plan.getStatus())
                      .append("\n");
        }
        
        showAlert("Meal Requirements", requirements.toString());
    }
    
    @FXML
    public void backfrommenubutton(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HeadChef/HeadChefDashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void filterspecialrequestsbutton(ActionEvent actionEvent) {
        String filter = filterSpecialRequestsComboBox.getValue();
        if (filter == null || filter.equals("All")) {
            viewlistofmealtableview.setItems(menuPlansList);
        } else {
            showAlert("Filter", "Filtering by: " + filter);
        }
    }
    
    @FXML
    public void resetspecialbutton(ActionEvent actionEvent) {
        clearForm();
        showAlert("Success", "Form reset successfully!");
    }
    
    @FXML
    public void showDataLocationButton(ActionEvent actionEvent) {
        showAlert("Data Location", "Menu plans are stored in: " + MENU_PLANS_FILE);
    }
    
    private void saveToBinaryFile(MenuPlan plan) {
        File file = new File(MENU_PLANS_FILE);
        file.getParentFile().mkdirs();
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true))) {
            oos.writeObject(plan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveAllPlans() {
        File file = new File(MENU_PLANS_FILE);
        file.getParentFile().mkdirs();
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (MenuPlan plan : menuPlansList) {
                oos.writeObject(plan);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void createEmptyFile() {
        File file = new File(MENU_PLANS_FILE);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void clearForm() {
        flightNumberTextField.clear();
        airlinenamemenutextfield.clear();
        datePicker.setValue(null);
        mealTypeComboBox.setValue(null);
        mealnamemenutextfield.clear();
        mealDescriptionTextArea.clear();
        passengerCountTextField.clear();
        mealCategoryComboBox.setValue(null);
        costPerMealTextField.clear();
        statusComboBox.setValue(null);
        specialMealCheckBox.setSelected(false);
        specialrequestareamenu.clear();
        dietaryRestrictionsTextArea.clear();
        notesTextArea.clear();
        filterSpecialRequestsComboBox.setValue(null);
        selectedPlan = null;
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
