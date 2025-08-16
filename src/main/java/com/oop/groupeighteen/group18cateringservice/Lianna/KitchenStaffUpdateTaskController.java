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

public class KitchenStaffUpdateTaskController {
    
    @FXML
    private TableView<AssignedTasks> progressmealpreogtableview;
    
    @FXML
    private TableColumn<AssignedTasks, String> tasknamemealprogpcol;
    
    @FXML
    private TableColumn<AssignedTasks, String> statusprogcol;
    
    @FXML
    private TableColumn<AssignedTasks, String> notesprogcol;
    
    @FXML
    private TextArea communicationTextArea;
    
    @FXML
    private Label totalTasksLabel;
    
    @FXML
    private Label delayedTasksLabel;
    
    @FXML
    private Label completedTasksLabel;
    
    @FXML
    private ComboBox<String> communicationTypeComboBox;
    
    @FXML
    private TextArea headChefInstructionsTextArea;
    
    private ObservableList<AssignedTasks> assignedTasksList = FXCollections.observableArrayList();
    private AssignedTasks selectedTask;
    private static final String ASSIGNED_TASKS_FILE = "assigned_tasks.bin";
    private static final String COMMUNICATION_FILE = "kitchen_communication.bin";
    
    @FXML
    public void initialize() {
        setupTableColumns();
        loadAssignedTasks();
        setupTableSelection();
        setupCommunicationTypeComboBox();
        updateStatistics();
    }
    
    private void setupTableColumns() {
        tasknamemealprogpcol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        statusprogcol.setCellValueFactory(new PropertyValueFactory<>("status"));
        notesprogcol.setCellValueFactory(new PropertyValueFactory<>("notes"));
    }
    
    private void setupCommunicationTypeComboBox() {
        communicationTypeComboBox.getItems().addAll(
            "Report Preparation Delay",
            "Report Problem/Issue",
            "Request Task Clarification",
            "Request Instructions",
            "Confirm Task Understanding",
            "General Communication"
        );
        communicationTypeComboBox.setValue("Report Preparation Delay");
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
        
        progressmealpreogtableview.setItems(assignedTasksList);
    }
    
    private void setupTableSelection() {
        progressmealpreogtableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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
            details.append("Task Type: ").append(task.getTaskType()).append("\n");
            if (task.getNotes() != null && !task.getNotes().isEmpty()) {
                details.append("Current Notes: ").append(task.getNotes()).append("\n");
            }
            details.append("\nCommunication History:\n");
            details.append("Select a task and use communication buttons below to interact with Head Chef.\n");
            
            communicationTextArea.setText(details.toString());
        }
    }
    
    private void updateStatistics() {
        int total = assignedTasksList.size();
        int delayed = 0;
        int completed = 0;
        
        for (AssignedTasks task : assignedTasksList) {
            if ("Ready".equals(task.getStatus()) || "Completed".equals(task.getStatus())) {
                completed++;
            } else if ("Delayed".equals(task.getStatus()) || "Problem".equals(task.getStatus())) {
                delayed++;
            }
        }
        
        totalTasksLabel.setText("Total Tasks: " + total);
        delayedTasksLabel.setText("Delayed: " + delayed);
        completedTasksLabel.setText("Completed: " + completed);
    }
    
    @FXML
    public void reportDelayButton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task first");
            return;
        }
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Report Preparation Delay");
        dialog.setHeaderText("Report delay for task: " + selectedTask.getTaskName());
        dialog.setContentText("Describe the delay and expected completion time:");
        
        dialog.showAndWait().ifPresent(delay -> {
            selectedTask.setStatus("Delayed");
            
            String communication = "DELAY REPORTED: " + delay + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            if (selectedTask.getNotes() == null || selectedTask.getNotes().isEmpty()) {
                selectedTask.setNotes(communication);
            } else {
                selectedTask.setNotes(selectedTask.getNotes() + "\n" + communication);
            }
            
            progressmealpreogtableview.refresh();
            displayTaskDetails(selectedTask);
            updateStatistics();
            saveAllTasks();
            saveCommunication("DELAY", delay);
            showAlert("Delay Reported", "Preparation delay reported successfully to Head Chef!");
        });
    }
    
    @FXML
    public void reportProblemButton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task first");
            return;
        }
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Report Problem/Issue");
        dialog.setHeaderText("Report problem for task: " + selectedTask.getTaskName());
        dialog.setContentText("Describe the problem or issue:");
        
        dialog.showAndWait().ifPresent(problem -> {
            selectedTask.setStatus("Problem");
            
            String communication = "PROBLEM REPORTED: " + problem + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            if (selectedTask.getNotes() == null || selectedTask.getNotes().isEmpty()) {
                selectedTask.setNotes(communication);
            } else {
                selectedTask.setNotes(selectedTask.getNotes() + "\n" + communication);
            }
            
            progressmealpreogtableview.refresh();
            displayTaskDetails(selectedTask);
            updateStatistics();
            saveAllTasks();
            saveCommunication("PROBLEM", problem);
            showAlert("Problem Reported", "Problem reported successfully to Head Chef!");
        });
    }
    
    @FXML
    public void requestClarificationButton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task first");
            return;
        }
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Request Task Clarification");
        dialog.setHeaderText("Request clarification for task: " + selectedTask.getTaskName());
        dialog.setContentText("What clarification do you need?");
        
        dialog.showAndWait().ifPresent(clarification -> {
            String communication = "CLARIFICATION REQUESTED: " + clarification + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            if (selectedTask.getNotes() == null || selectedTask.getNotes().isEmpty()) {
                selectedTask.setNotes(communication);
            } else {
                selectedTask.setNotes(selectedTask.getNotes() + "\n" + communication);
            }
            
            progressmealpreogtableview.refresh();
            displayTaskDetails(selectedTask);
            saveAllTasks();
            saveCommunication("CLARIFICATION_REQUEST", clarification);
            showAlert("Clarification Requested", "Clarification request sent to Head Chef!");
        });
    }
    
    @FXML
    public void requestInstructionsButton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task first");
            return;
        }
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Request Instructions");
        dialog.setHeaderText("Request instructions for task: " + selectedTask.getTaskName());
        dialog.setContentText("What specific instructions do you need?");
        
        dialog.showAndWait().ifPresent(instructions -> {
            String communication = "INSTRUCTIONS REQUESTED: " + instructions + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            if (selectedTask.getNotes() == null || selectedTask.getNotes().isEmpty()) {
                selectedTask.setNotes(communication);
            } else {
                selectedTask.setNotes(selectedTask.getNotes() + "\n" + communication);
            }
            
            progressmealpreogtableview.refresh();
            displayTaskDetails(selectedTask);
            saveAllTasks();
            saveCommunication("INSTRUCTIONS_REQUEST", instructions);
            showAlert("Instructions Requested", "Instructions request sent to Head Chef!");
        });
    }
    
    @FXML
    public void confirmUnderstandingButton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task first");
            return;
        }
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Confirm Understanding");
        dialog.setHeaderText("Confirm understanding for task: " + selectedTask.getTaskName());
        dialog.setContentText("Please confirm your understanding of the instructions:");
        
        dialog.showAndWait().ifPresent(confirmation -> {
            String communication = "UNDERSTANDING CONFIRMED: " + confirmation + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            if (selectedTask.getNotes() == null || selectedTask.getNotes().isEmpty()) {
                selectedTask.setNotes(communication);
            } else {
                selectedTask.setNotes(selectedTask.getNotes() + "\n" + communication);
            }
            
            progressmealpreogtableview.refresh();
            displayTaskDetails(selectedTask);
            saveAllTasks();
            saveCommunication("UNDERSTANDING_CONFIRMED", confirmation);
            showAlert("Understanding Confirmed", "Understanding confirmed and sent to Head Chef!");
        });
    }
    
    @FXML
    public void viewHeadChefInstructionsButton(ActionEvent actionEvent) {
        StringBuilder instructions = new StringBuilder();
        instructions.append("=== HEAD CHEF INSTRUCTIONS ===\n\n");
        
        for (AssignedTasks task : assignedTasksList) {
            if (task.getNotes() != null && !task.getNotes().isEmpty()) {
                instructions.append("Task: ").append(task.getTaskName()).append("\n");
                instructions.append("Staff: ").append(task.getStaffName()).append("\n");
                instructions.append("Status: ").append(task.getStatus()).append("\n");
                instructions.append("Instructions/Notes: ").append(task.getNotes()).append("\n");
                instructions.append("------------------------\n");
            }
        }
        
        if (instructions.toString().equals("=== HEAD CHEF INSTRUCTIONS ===\n\n")) {
            instructions.append("No instructions available yet.");
        }
        
        headChefInstructionsTextArea.setText(instructions.toString());
    }
    
    @FXML
    public void submitprogbutton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task to submit progress");
            return;
        }
        
        String communicationType = communicationTypeComboBox.getValue();
        if (communicationType == null) {
            showAlert("Error", "Please select a communication type");
            return;
        }
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Submit Progress Report");
        dialog.setHeaderText("Submit progress for: " + communicationType);
        dialog.setContentText("Enter your progress report:");
        
        dialog.showAndWait().ifPresent(progress -> {
            String communication = communicationType.toUpperCase() + ": " + progress + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            if (selectedTask.getNotes() == null || selectedTask.getNotes().isEmpty()) {
                selectedTask.setNotes(communication);
            } else {
                selectedTask.setNotes(selectedTask.getNotes() + "\n" + communication);
            }
            
            progressmealpreogtableview.refresh();
            displayTaskDetails(selectedTask);
            saveAllTasks();
            saveCommunication(communicationType, progress);
            showAlert("Progress Submitted", "Progress report submitted successfully to Head Chef!");
        });
    }
    
    @FXML
    public void backfrommealprogbutton(ActionEvent actionEvent) {
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
    
    private void saveCommunication(String type, String message) {
        File file = new File(COMMUNICATION_FILE);
        file.getParentFile().mkdirs();
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true))) {
            String communication = type + ": " + message + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            oos.writeObject(communication);
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
