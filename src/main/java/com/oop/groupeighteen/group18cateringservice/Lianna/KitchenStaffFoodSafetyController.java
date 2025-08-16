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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.oop.groupeighteen.group18cateringservice.Lianna.models.AssignedTasks;

public class KitchenStaffFoodSafetyController {
    
    @FXML
    private TableView<AssignedTasks> safetyTasksTable;
    
    @FXML
    private TableColumn<AssignedTasks, String> taskNameCol;
    
    @FXML
    private TableColumn<AssignedTasks, String> safetyStatusCol;
    
    @FXML
    private TableColumn<AssignedTasks, String> hygieneNotesCol;
    
    @FXML
    private TextArea safetyReportTextArea;
    
    @FXML
    private Label totalTasksLabel;
    
    @FXML
    private Label completedTasksLabel;
    
    @FXML
    private Label pendingTasksLabel;
    
    @FXML
    private CheckBox handsWashedCheckBox;
    
    @FXML
    private CheckBox glovesUsedCheckBox;
    
    @FXML
    private CheckBox workAreaCleanCheckBox;
    
    @FXML
    private CheckBox wasteDisposedCheckBox;
    
    private ObservableList<AssignedTasks> assignedTasksList = FXCollections.observableArrayList();
    private AssignedTasks selectedTask;
    private static final String ASSIGNED_TASKS_FILE = "assigned_tasks.bin";
    private static final String SAFETY_REPORT_FILE = "safety_reports.bin";
    
    @FXML
    public void initialize() {
        setupTableColumns();
        loadAssignedTasks();
        setupTableSelection();
        updateStatistics();
    }
    
    private void setupTableColumns() {
        taskNameCol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        safetyStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        hygieneNotesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));
    }
    
    private void loadAssignedTasks() {
        assignedTasksList.clear();
        File file = new File(ASSIGNED_TASKS_FILE);
        
        if (!file.exists()) {
            createEmptyFile();
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof AssignedTasks) {
                        AssignedTasks task = (AssignedTasks) obj;
                        assignedTasksList.add(task);
                    } else if (obj instanceof ArrayList) {
                        ArrayList<AssignedTasks> tasks = (ArrayList<AssignedTasks>) obj;
                        assignedTasksList.addAll(tasks);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        safetyTasksTable.setItems(assignedTasksList);
    }
    
    private void setupTableSelection() {
        safetyTasksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedTask = newSelection;
                displayTaskDetails(newSelection);
            }
        });
    }
    
    private void displayTaskDetails(AssignedTasks task) {
        if (task != null) {
            StringBuilder details = new StringBuilder();
            details.append("Task Details:\n");
            details.append("Task Name: ").append(task.getTaskName()).append("\n");
            details.append("Staff: ").append(task.getStaffName()).append("\n");
            details.append("Flight: ").append(task.getFlightNumber()).append("\n");
            details.append("Status: ").append(task.getStatus()).append("\n");
            details.append("Due Date: ").append(task.getDueDate()).append("\n");
            if (task.getNotes() != null && !task.getNotes().isEmpty()) {
                details.append("Notes: ").append(task.getNotes()).append("\n");
            }
            details.append("\nHygiene Checklist:\n");
            details.append("- Hands washed and gloves used: ").append(task.getStatus().equals("In Progress") ? "Required" : "Not Started").append("\n");
            details.append("- Work area maintained clean: ").append(task.getStatus().equals("In Progress") ? "Required" : "Not Started").append("\n");
            details.append("- Waste properly disposed: ").append(task.getStatus().equals("In Progress") ? "Required" : "Not Started").append("\n");
            
            safetyReportTextArea.setText(details.toString());
        }
    }
    
    private void updateStatistics() {
        int total = assignedTasksList.size();
        int completed = 0;
        int pending = 0;
        
        for (AssignedTasks task : assignedTasksList) {
            if ("Ready".equals(task.getStatus()) || "Completed".equals(task.getStatus())) {
                completed++;
            } else {
                pending++;
            }
        }
        
        totalTasksLabel.setText("Total Tasks: " + total);
        completedTasksLabel.setText("Completed: " + completed);
        pendingTasksLabel.setText("Pending: " + pending);
    }
    
    @FXML
    public void washHandsButton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task first");
            return;
        }
        
        handsWashedCheckBox.setSelected(true);
        glovesUsedCheckBox.setSelected(true);
        
        if (selectedTask.getNotes() == null || selectedTask.getNotes().isEmpty()) {
            selectedTask.setNotes("Hygiene: Hands washed and gloves used - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        } else {
            selectedTask.setNotes(selectedTask.getNotes() + "\nHygiene: Hands washed and gloves used - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        }
        
        safetyTasksTable.refresh();
        displayTaskDetails(selectedTask);
        saveAllTasks();
        showAlert("Success", "Hygiene practice recorded: Hands washed and gloves used");
    }
    
    @FXML
    public void maintainCleanAreaButton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task first");
            return;
        }
        
        workAreaCleanCheckBox.setSelected(true);
        
        if (selectedTask.getNotes() == null || selectedTask.getNotes().isEmpty()) {
            selectedTask.setNotes("Hygiene: Work area maintained clean - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        } else {
            selectedTask.setNotes(selectedTask.getNotes() + "\nHygiene: Work area maintained clean - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        }
        
        safetyTasksTable.refresh();
        displayTaskDetails(selectedTask);
        saveAllTasks();
        showAlert("Success", "Hygiene practice recorded: Work area maintained clean");
    }
    
    @FXML
    public void disposeWasteButton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task first");
            return;
        }
        
        wasteDisposedCheckBox.setSelected(true);
        
        if (selectedTask.getNotes() == null || selectedTask.getNotes().isEmpty()) {
            selectedTask.setNotes("Hygiene: Waste properly disposed - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        } else {
            selectedTask.setNotes(selectedTask.getNotes() + "\nHygiene: Waste properly disposed - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        }
        
        safetyTasksTable.refresh();
        displayTaskDetails(selectedTask);
        saveAllTasks();
        showAlert("Success", "Hygiene practice recorded: Waste properly disposed");
    }
    
    @FXML
    public void reportSafetyHazardButton(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Report Safety Hazard");
        dialog.setHeaderText("Report any safety hazards or issues");
        dialog.setContentText("Describe the safety hazard or issue:");
        
        dialog.showAndWait().ifPresent(hazard -> {
            if (selectedTask != null) {
                if (selectedTask.getNotes() == null || selectedTask.getNotes().isEmpty()) {
                    selectedTask.setNotes("SAFETY HAZARD REPORTED: " + hazard + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
                } else {
                    selectedTask.setNotes(selectedTask.getNotes() + "\nSAFETY HAZARD REPORTED: " + hazard + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
                }
                safetyTasksTable.refresh();
                displayTaskDetails(selectedTask);
                saveAllTasks();
            }
            
            saveSafetyReport(hazard);
            showAlert("Safety Report", "Safety hazard reported successfully!\n\nHazard: " + hazard);
        });
    }
    
    @FXML
    public void updateTaskProgressButton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task to update");
            return;
        }
        
        if (!handsWashedCheckBox.isSelected() || !glovesUsedCheckBox.isSelected()) {
            showAlert("Warning", "Please ensure hands are washed and gloves are used before proceeding");
            return;
        }
        
        if (!workAreaCleanCheckBox.isSelected()) {
            showAlert("Warning", "Please ensure work area is clean before proceeding");
            return;
        }
        
        if (!wasteDisposedCheckBox.isSelected()) {
            showAlert("Warning", "Please ensure waste is properly disposed before proceeding");
            return;
        }
        
        selectedTask.setStatus("In Progress");
        selectedTask.setCompleted(false);
        
        if (selectedTask.getNotes() == null || selectedTask.getNotes().isEmpty()) {
            selectedTask.setNotes("Task Progress Updated - All hygiene practices completed - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        } else {
            selectedTask.setNotes(selectedTask.getNotes() + "\nTask Progress Updated - All hygiene practices completed - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        }
        
        safetyTasksTable.refresh();
        displayTaskDetails(selectedTask);
        updateStatistics();
        saveAllTasks();
        showAlert("Success", "Task progress updated successfully with all hygiene practices completed!");
    }
    
    @FXML
    public void viewSafetyReportsButton(ActionEvent actionEvent) {
        StringBuilder reports = new StringBuilder();
        reports.append("=== SAFETY AND HYGIENE REPORTS ===\n\n");
        
        for (AssignedTasks task : assignedTasksList) {
            if (task.getNotes() != null && !task.getNotes().isEmpty()) {
                reports.append("Task: ").append(task.getTaskName()).append("\n");
                reports.append("Staff: ").append(task.getStaffName()).append("\n");
                reports.append("Status: ").append(task.getStatus()).append("\n");
                reports.append("Notes: ").append(task.getNotes()).append("\n");
                reports.append("------------------------\n");
            }
        }
        
        if (reports.toString().equals("=== SAFETY AND HYGIENE REPORTS ===\n\n")) {
            reports.append("No safety reports available yet.");
        }
        
        TextArea textArea = new TextArea(reports.toString());
        textArea.setEditable(false);
        textArea.setPrefRowCount(20);
        textArea.setPrefColumnCount(60);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Safety and Hygiene Reports");
        alert.setHeaderText("All Safety and Hygiene Reports");
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }
    
    @FXML
    public void backToDashboardButton(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("KitchenStaff/KitchenStaffDashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveSafetyReport(String hazard) {
        File file = new File(SAFETY_REPORT_FILE);
        file.getParentFile().mkdirs();
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true))) {
            oos.writeObject("SAFETY HAZARD: " + hazard + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveAllTasks() {
        File file = new File(ASSIGNED_TASKS_FILE);
        file.getParentFile().mkdirs();
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (AssignedTasks task : assignedTasksList) {
                oos.writeObject(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void createEmptyFile() {
        File file = new File(ASSIGNED_TASKS_FILE);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
