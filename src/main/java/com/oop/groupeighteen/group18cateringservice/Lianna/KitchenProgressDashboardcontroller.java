package com.oop.groupeighteen.group18cateringservice.Lianna;

import com.oop.groupeighteen.group18cateringservice.Lianna.models.AssignedTasks;
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

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KitchenProgressDashboardcontroller {
    
    @FXML
    private TableView<AssignedTasks> progresstableview;
    @FXML
    private TableColumn<AssignedTasks, String> flightnoprogresscol;
    @FXML
    private TableColumn<AssignedTasks, String> mealitemprogresscol;
    @FXML
    private TableColumn<AssignedTasks, String> staffnameprogresscol;
    @FXML
    private TableColumn<AssignedTasks, String> statusprogresscol;
    @FXML
    private TextArea issueareaprogress;
    
    private ObservableList<AssignedTasks> tasksList = FXCollections.observableArrayList();
    private static final String ASSIGNED_TASKS_FILE = "assigned_tasks.bin";
    
    @FXML
    public void initialize() {
        setupTableColumns();
        loadTasksFromFile();
        updateStatistics();
        setupTableSelection();
    }
    
    private void setupTableColumns() {
        flightnoprogresscol.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        mealitemprogresscol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        staffnameprogresscol.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        statusprogresscol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    
    private void setupTableSelection() {
        progresstableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                issueareaprogress.setText(newSelection.getNotes() != null ? newSelection.getNotes() : "No issues reported");
            }
        });
    }
    
    private void loadTasksFromFile() {
        tasksList.clear();
        
        try {
            File file = new File(ASSIGNED_TASKS_FILE);
            if (!file.exists()) {
                return;
            }
            
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ASSIGNED_TASKS_FILE));
            
            try {
                while (true) {
                    Object obj = ois.readObject();
                    if (obj instanceof AssignedTasks) {
                        tasksList.add((AssignedTasks) obj);
                    } else if (obj instanceof List) {
                        List<?> list = (List<?>) obj;
                        for (Object item : list) {
                            if (item instanceof AssignedTasks) {
                                tasksList.add((AssignedTasks) item);
                            }
                        }
                        break;
                    }
                }
            } catch (EOFException e) {
            }
            
            ois.close();
            progresstableview.setItems(tasksList);
            
        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Failed to load tasks: " + e.getMessage());
        }
    }
    
    private void updateStatistics() {
    }
    
    @FXML
    private void viewselectedstaffissuesbutton(ActionEvent event) {
        AssignedTasks selectedTask = progresstableview.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            showAlert("Information", "Please select a task to view issues");
            return;
        }
        
        StringBuilder issues = new StringBuilder();
        issues.append("STAFF ISSUES REPORT\n");
        issues.append("Staff: ").append(selectedTask.getStaffName()).append("\n");
        issues.append("Task: ").append(selectedTask.getTaskName()).append("\n");
        issues.append("Flight: ").append(selectedTask.getFlightNumber()).append("\n");
        issues.append("Status: ").append(selectedTask.getStatus()).append("\n\n");
        issues.append("Issues/Notes:\n");
        issues.append(selectedTask.getNotes() != null ? selectedTask.getNotes() : "No issues reported");
        
        showAlert("Staff Issues", issues.toString());
    }
    
    @FXML
    private void logcompletionsummarybutton(ActionEvent event) {
        if (tasksList.isEmpty()) {
            showAlert("Information", "No tasks available for completion summary");
            return;
        }
        
        StringBuilder summary = new StringBuilder();
        summary.append("COMPLETION SUMMARY REPORT\n");
        summary.append("Generated: ").append(LocalDateTime.now().toString().substring(0, 16)).append("\n\n");
        
        Map<String, List<AssignedTasks>> tasksByStatus = tasksList.stream()
                .collect(Collectors.groupingBy(AssignedTasks::getStatus));
        
        int totalTasks = tasksList.size();
        long completedTasks = tasksList.stream().filter(task -> "Completed".equals(task.getStatus())).count();
        long inProgressTasks = tasksList.stream().filter(task -> "In Progress".equals(task.getStatus())).count();
        long pendingTasks = tasksList.stream().filter(task -> "Assigned".equals(task.getStatus()) || "Pending".equals(task.getStatus())).count();
        
        summary.append("SUMMARY STATISTICS:\n");
        summary.append("Total Tasks: ").append(totalTasks).append("\n");
        summary.append("Completed: ").append(completedTasks).append(" (").append(completedTasks * 100 / Math.max(totalTasks, 1)).append("%)").append("\n");
        summary.append("In Progress: ").append(inProgressTasks).append("\n");
        summary.append("Pending: ").append(pendingTasks).append("\n\n");
        
        for (Map.Entry<String, List<AssignedTasks>> entry : tasksByStatus.entrySet()) {
            summary.append("STATUS: ").append(entry.getKey().toUpperCase()).append(" (").append(entry.getValue().size()).append(" tasks)\n");
            
            for (AssignedTasks task : entry.getValue()) {
                summary.append("  • ").append(task.getStaffName())
                      .append(" - ").append(task.getTaskName())
                      .append(" [Flight: ").append(task.getFlightNumber()).append("]\n");
            }
            summary.append("\n");
        }
        
        showAlert("Completion Summary", summary.toString());
    }
    
    @FXML
    private void backfromkitchenprogressbutton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HeadChef/HeadChefDashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
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
