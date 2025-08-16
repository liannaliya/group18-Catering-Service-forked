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
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.oop.groupeighteen.group18cateringservice.Lianna.models.AssignedTasks;
import javafx.scene.Node;

public class KitchenStaffStartMealPrepController {
    
    @FXML
    private TableView<AssignedTasks> preptableview;
    
    @FXML
    private TableColumn<AssignedTasks, String> taskprepcol;
    
    @FXML
    private TableColumn<AssignedTasks, String> Notespeprepcol;
    
    @FXML
    private TableColumn<AssignedTasks, String> statuspremcol;
    
    private ObservableList<AssignedTasks> assignedTasksList = FXCollections.observableArrayList();
    private AssignedTasks selectedTask;
    private static final String ASSIGNED_TASKS_FILE = "assigned_tasks.bin";
    
    @FXML
    public void initialize() {
        setupTableColumns();
        loadAssignedTasks();
        setupTableSelection();
    }
    
    private void setupTableColumns() {
        taskprepcol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        Notespeprepcol.setCellValueFactory(new PropertyValueFactory<>("notes"));
        statuspremcol.setCellValueFactory(new PropertyValueFactory<>("status"));
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
        
        preptableview.setItems(assignedTasksList);
    }
    
    private void setupTableSelection() {
        preptableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedTask = newSelection;
            }
        });
    }
    
    @FXML
    public void starttaskbutton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task to start");
            return;
        }
        
        selectedTask.setStatus("In Progress");
        selectedTask.setCompleted(false);
        preptableview.refresh();
        saveAllTasks();
        showAlert("Success", "Task started successfully!");
    }
    
    @FXML
    public void MarkReadyButton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task to mark as ready");
            return;
        }
        
        selectedTask.setStatus("Ready");
        selectedTask.setCompleted(true);
        preptableview.refresh();
        saveAllTasks();
        showAlert("Success", "Task marked as ready!");
    }
    
    @FXML
    public void uploadstatusbutton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task to upload status");
            return;
        }
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Upload Notes");
        dialog.setHeaderText("Add notes for task: " + selectedTask.getTaskName());
        dialog.setContentText("Enter notes:");
        
        dialog.showAndWait().ifPresent(notes -> {
            selectedTask.setNotes(notes);
            preptableview.refresh();
            saveAllTasks();
            showAlert("Success", "Notes uploaded successfully!");
        });
    }
    
    @FXML
    public void confirmschedulebutton(ActionEvent actionEvent) {
        if (assignedTasksList.isEmpty()) {
            showAlert("Error", "No tasks to confirm");
            return;
        }
        
        StringBuilder schedule = new StringBuilder();
        schedule.append("=== MEAL PREPARATION SCHEDULE ===\n\n");
        
        for (AssignedTasks task : assignedTasksList) {
            schedule.append("Task: ").append(task.getTaskName()).append("\n");
            schedule.append("Staff: ").append(task.getStaffName()).append("\n");
            schedule.append("Status: ").append(task.getStatus()).append("\n");
            schedule.append("Flight: ").append(task.getFlightNumber()).append("\n");
            schedule.append("Due: ").append(task.getDueDate()).append("\n");
            if (task.getNotes() != null && !task.getNotes().isEmpty()) {
                schedule.append("Notes: ").append(task.getNotes()).append("\n");
            }
            schedule.append("------------------------\n");
        }
        
        TextArea textArea = new TextArea(schedule.toString());
        textArea.setEditable(false);
        textArea.setPrefRowCount(20);
        textArea.setPrefColumnCount(50);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmed Schedule");
        alert.setHeaderText("Meal Preparation Schedule Confirmed");
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
        
        showAlert("Success", "Schedule confirmed and saved!");
    }
    
    @FXML
    public void dispatchplanprembutton(ActionEvent actionEvent) {
        StringBuilder dispatchPlan = new StringBuilder();
        dispatchPlan.append("=== DISPATCH PLAN FOR DELIVERY TEAM ===\n\n");
        
        for (AssignedTasks task : assignedTasksList) {
            if ("Ready".equals(task.getStatus())) {
                dispatchPlan.append("READY FOR DISPATCH:\n");
                dispatchPlan.append("Task: ").append(task.getTaskName()).append("\n");
                dispatchPlan.append("Flight: ").append(task.getFlightNumber()).append("\n");
                dispatchPlan.append("Task Type: ").append(task.getTaskType()).append("\n");
                dispatchPlan.append("Staff: ").append(task.getStaffName()).append("\n");
                if (task.getNotes() != null && !task.getNotes().isEmpty()) {
                    dispatchPlan.append("Special Instructions: ").append(task.getNotes()).append("\n");
                }
                dispatchPlan.append("------------------------\n");
            }
        }
        
        if (dispatchPlan.toString().equals("=== DISPATCH PLAN FOR DELIVERY TEAM ===\n\n")) {
            dispatchPlan.append("No items ready for dispatch yet.");
        }
        
        TextArea textArea = new TextArea(dispatchPlan.toString());
        textArea.setEditable(false);
        textArea.setPrefRowCount(20);
        textArea.setPrefColumnCount(50);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dispatch Plan");
        alert.setHeaderText("Dispatch Plan for Delivery Team");
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }
    
    @FXML
    public void backfrommealprembutton(ActionEvent actionEvent) {
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
