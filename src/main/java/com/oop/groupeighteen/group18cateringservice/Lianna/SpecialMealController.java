package com.oop.groupeighteen.group18cateringservice.Lianna;

import com.oop.groupeighteen.group18cateringservice.Lianna.models.SpecialMeal;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SpecialMealController {
    @javafx.fxml.FXML
    private TableView<SpecialMeal> requeststableview;
    @javafx.fxml.FXML
    private TableColumn passengernamespecialcol;
    @javafx.fxml.FXML
    private TableColumn statusspecialcol;
    @javafx.fxml.FXML
    private TableColumn mealtypespecialcol;
    @javafx.fxml.FXML
    private ComboBox<String> statusComboBox;
    @javafx.fxml.FXML
    private TableColumn flightnospecialcol;
    @javafx.fxml.FXML
    private ComboBox<String> mealTypeComboBox;
    @javafx.fxml.FXML
    private TextField passengerNameField;
    @javafx.fxml.FXML
    private TextField flightNumberField;
    @javafx.fxml.FXML
    private ComboBox<String> dietaryRequirementComboBox;
    @javafx.fxml.FXML
    private DatePicker dueDatePicker;
    @javafx.fxml.FXML
    private TextArea specialInstructionsTextArea;
    @javafx.fxml.FXML
    private TextField assignedStaffField;
    @javafx.fxml.FXML
    private TextArea preparationInstructionsTextArea;
    @javafx.fxml.FXML
    private CheckBox completedCheckBox;
    @javafx.fxml.FXML
    private CheckBox qualityVerifiedCheckBox;
    @javafx.fxml.FXML
    private TableColumn<SpecialMeal, String> dietaryRequirementCol;
    @javafx.fxml.FXML
    private TableColumn<SpecialMeal, String> assignedStaffCol;
    @javafx.fxml.FXML
    private TableColumn<SpecialMeal, LocalDateTime> dueDateCol;
    @javafx.fxml.FXML
    private TableColumn<SpecialMeal, Boolean> completedCol;

    private List<SpecialMeal> specialMeals = new ArrayList<>();
    private SpecialMeal selectedMeal;
    private static final String SPECIAL_MEALS_FILE = "special_meals.bin";

    @javafx.fxml.FXML
    public void initialize() {
        setupTableColumns();
        setupComboBoxes();
        loadSpecialMeals();
        setupTableSelection();
    }

    private void setupTableColumns() {
        flightnospecialcol.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        passengernamespecialcol.setCellValueFactory(new PropertyValueFactory<>("passengerName"));
        mealtypespecialcol.setCellValueFactory(new PropertyValueFactory<>("mealType"));
        dietaryRequirementCol.setCellValueFactory(new PropertyValueFactory<>("dietaryRequirement"));
        statusspecialcol.setCellValueFactory(new PropertyValueFactory<>("status"));
        assignedStaffCol.setCellValueFactory(new PropertyValueFactory<>("assignedStaff"));
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        completedCol.setCellValueFactory(new PropertyValueFactory<>("completed"));
    }

    private void setupComboBoxes() {
        mealTypeComboBox.getItems().addAll("Breakfast", "Lunch", "Dinner", "Snack");
        dietaryRequirementComboBox.getItems().addAll("Vegetarian", "Vegan", "Gluten-Free", "Dairy-Free", "Nut-Free", "Halal", "Kosher", "Diabetic", "Low-Sodium", "Low-Fat");
        statusComboBox.getItems().addAll("Pending", "Approved", "In Preparation", "Completed", "Quality Verified", "Rejected");
    }

    private void setupTableSelection() {
        requeststableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedMeal = newSelection;
                loadMealToForm(selectedMeal);
            }
        });
    }

    private void loadMealToForm(SpecialMeal meal) {
        passengerNameField.setText(meal.getPassengerName());
        flightNumberField.setText(meal.getFlightNumber());
        mealTypeComboBox.setValue(meal.getMealType());
        dietaryRequirementComboBox.setValue(meal.getDietaryRequirement());
        dueDatePicker.setValue(meal.getDueDate().toLocalDate());
        specialInstructionsTextArea.setText(meal.getSpecialInstructions());
        assignedStaffField.setText(meal.getAssignedStaff());
        preparationInstructionsTextArea.setText(meal.getPreparationInstructions());
        statusComboBox.setValue(meal.getStatus());
        completedCheckBox.setSelected(meal.isCompleted());
        qualityVerifiedCheckBox.setSelected(meal.isQualityVerified());
    }

    private void loadSpecialMeals() {
        ObjectInputStream ois = null;
        try {
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }

            File file = new File(SPECIAL_MEALS_FILE);
            if (!file.exists()) {
                createEmptyFile();
                return;
            }

            ois = new ObjectInputStream(new FileInputStream(SPECIAL_MEALS_FILE));
            requeststableview.getItems().clear();
            specialMeals.clear();

            try {
                while (true) {
                    Object obj = ois.readObject();
                    if (obj instanceof SpecialMeal) {
                        SpecialMeal meal = (SpecialMeal) obj;
                        specialMeals.add(meal);
                        requeststableview.getItems().add(meal);
                    } else if (obj instanceof java.util.List) {
                        java.util.List<?> list = (java.util.List<?>) obj;
                        for (Object item : list) {
                            if (item instanceof SpecialMeal) {
                                SpecialMeal meal = (SpecialMeal) item;
                                specialMeals.add(meal);
                                requeststableview.getItems().add(meal);
                            }
                        }
                        break;
                    }
                    if (ois.available() == 0) {
                        break;
                    }
                }
            } catch (EOFException e) {
            }

        } catch (ClassNotFoundException e) {
            createEmptyFile();
        } catch (IOException e) {
            createEmptyFile();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createEmptyFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SPECIAL_MEALS_FILE));
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void addSpecialMealButton(ActionEvent actionEvent) {
        try {
            if (passengerNameField.getText().isEmpty() || flightNumberField.getText().isEmpty() ||
                    mealTypeComboBox.getValue() == null || dietaryRequirementComboBox.getValue() == null ||
                    dueDatePicker.getValue() == null) {
                showAlert("Error", "Please fill in all required fields");
                return;
            }

            SpecialMeal newMeal = new SpecialMeal(
                    "SM" + System.currentTimeMillis(),
                    passengerNameField.getText(),
                    flightNumberField.getText(),
                    mealTypeComboBox.getValue(),
                    dietaryRequirementComboBox.getValue(),
                    specialInstructionsTextArea.getText(),
                    dueDatePicker.getValue().atStartOfDay(),
                    "Chef Lianna"
            );

            specialMeals.add(newMeal);
            requeststableview.getItems().add(newMeal);

            saveToBinaryFile(newMeal);
            showAlert("Success", "Special meal request added successfully!");
            clearForm();

        } catch (Exception e) {
            showAlert("Error", "An error occurred while adding special meal request: " + e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void updateMealButton(ActionEvent actionEvent) {
        if (selectedMeal == null) {
            showAlert("Error", "Please select a special meal request to update");
            return;
        }

        try {
            selectedMeal.setPassengerName(passengerNameField.getText());
            selectedMeal.setFlightNumber(flightNumberField.getText());
            selectedMeal.setMealType(mealTypeComboBox.getValue());
            selectedMeal.setDietaryRequirement(dietaryRequirementComboBox.getValue());
            selectedMeal.setDueDate(dueDatePicker.getValue().atStartOfDay());
            selectedMeal.setSpecialInstructions(specialInstructionsTextArea.getText());
            selectedMeal.setAssignedStaff(assignedStaffField.getText());
            selectedMeal.setPreparationInstructions(preparationInstructionsTextArea.getText());
            selectedMeal.setStatus(statusComboBox.getValue());
            selectedMeal.setCompleted(completedCheckBox.isSelected());
            selectedMeal.setQualityVerified(qualityVerifiedCheckBox.isSelected());
            selectedMeal.setLastModified(LocalDateTime.now());

            requeststableview.refresh();
            saveAllSpecialMeals();
            showAlert("Success", "Special meal request updated successfully!");
            clearForm();

        } catch (Exception e) {
            showAlert("Error", "An error occurred while updating special meal request: " + e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void assignTaskButton(ActionEvent actionEvent) {
        if (selectedMeal == null) {
            showAlert("Error", "Please select a special meal request to assign task");
            return;
        }

        if (assignedStaffField.getText().isEmpty()) {
            showAlert("Error", "Please enter assigned staff name");
            return;
        }

        selectedMeal.setAssignedStaff(assignedStaffField.getText());
        selectedMeal.setStatus("In Preparation");
        selectedMeal.setLastModified(LocalDateTime.now());

        requeststableview.refresh();
        saveAllSpecialMeals();
        showAlert("Success", "Task assigned to " + assignedStaffField.getText() + " for " + selectedMeal.getPassengerName());
    }

    @javafx.fxml.FXML
    public void verifyQualityButton(ActionEvent actionEvent) {
        if (selectedMeal == null) {
            showAlert("Error", "Please select a special meal request to verify quality");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Quality Verification");
        dialog.setHeaderText("Special Meal for: " + selectedMeal.getPassengerName());
        dialog.setContentText("Enter quality verification notes:");

        dialog.showAndWait().ifPresent(notes -> {
            selectedMeal.setQualityNotes(notes);
            selectedMeal.setQualityVerified(true);
            selectedMeal.setStatus("Quality Verified");
            selectedMeal.setLastModified(LocalDateTime.now());
            qualityVerifiedCheckBox.setSelected(true);
            requeststableview.refresh();
            saveAllSpecialMeals();
            showAlert("Success", "Quality verification completed for " + selectedMeal.getPassengerName());
        });
    }

    @javafx.fxml.FXML
    public void approveRequestButton(ActionEvent actionEvent) {
        if (selectedMeal == null) {
            showAlert("Error", "Please select a special meal request to approve");
            return;
        }

        selectedMeal.setStatus("Approved");
        selectedMeal.setLastModified(LocalDateTime.now());
        requeststableview.refresh();
        saveAllSpecialMeals();
        showAlert("Success", "Special meal request approved for " + selectedMeal.getPassengerName());
    }

    @javafx.fxml.FXML
    public void rejectRequestButton(ActionEvent actionEvent) {
        if (selectedMeal == null) {
            showAlert("Error", "Please select a special meal request to reject");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Reject Special Meal Request");
        dialog.setHeaderText("Special Meal for: " + selectedMeal.getPassengerName());
        dialog.setContentText("Enter rejection reason:");

        dialog.showAndWait().ifPresent(reason -> {
            selectedMeal.setStatus("Rejected");
            selectedMeal.setSpecialInstructions("REJECTED: " + reason);
            selectedMeal.setLastModified(LocalDateTime.now());
            requeststableview.refresh();
            saveAllSpecialMeals();
            showAlert("Success", "Special meal request rejected for " + selectedMeal.getPassengerName());
        });
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
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

    @javafx.fxml.FXML
    public void saveChangesButton(ActionEvent actionEvent) {
        saveAllSpecialMeals();
        showAlert("Success", "All special meal changes saved successfully!");
    }

    private void saveToBinaryFile(SpecialMeal meal) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SPECIAL_MEALS_FILE, true)) {
                @Override
                protected void writeStreamHeader() throws IOException {
                }
            };

            oos.writeObject(meal);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAllSpecialMeals() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SPECIAL_MEALS_FILE));
            for (SpecialMeal meal : specialMeals) {
                oos.writeObject(meal);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        passengerNameField.clear();
        flightNumberField.clear();
        mealTypeComboBox.setValue(null);
        dietaryRequirementComboBox.setValue(null);
        dueDatePicker.setValue(null);
        specialInstructionsTextArea.clear();
        assignedStaffField.clear();
        preparationInstructionsTextArea.clear();
        statusComboBox.setValue(null);
        completedCheckBox.setSelected(false);
        qualityVerifiedCheckBox.setSelected(false);
        selectedMeal = null;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
