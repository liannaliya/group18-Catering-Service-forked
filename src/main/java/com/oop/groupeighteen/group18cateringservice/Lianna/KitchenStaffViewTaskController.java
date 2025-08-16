package com.oop.groupeighteen.group18cateringservice.Lianna;

import com.oop.groupeighteen.group18cateringservice.Lianna.models.AssignedTasks;
import com.oop.groupeighteen.group18cateringservice.Lianna.models.KitchenStaffTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KitchenStaffViewTaskController {
    @FXML
    private TableView<AssignedTasks> assignedTasksTable;
    
    @FXML
    private TableColumn<AssignedTasks, String> taskIdCol;
    @FXML
    private TableColumn<AssignedTasks, String> staffNameCol;
    @FXML
    private TableColumn<AssignedTasks, String> taskTypeCol;
    @FXML
    private TableColumn<AssignedTasks, String> flightNumberCol;
    @FXML
    private TableColumn<AssignedTasks, LocalDateTime> dueDateCol;
    @FXML
    private TableColumn<AssignedTasks, String> statusCol;
    @FXML
    private TableColumn<AssignedTasks, Integer> workloadCol;
    @FXML
    private TableColumn<AssignedTasks, String> assignedByCol;
    
    @FXML
    private TextArea taskDetailsTextArea;
    @FXML
    private Label totalTasksLabel;
    @FXML
    private Label pendingTasksLabel;
    @FXML
    private Label acknowledgedTasksLabel;
    @FXML
    private Label completedTasksLabel;
    
    @FXML
    private ComboBox<String> filterStatusComboBox;
    @FXML
    private ComboBox<String> filterPriorityComboBox;
    
    @FXML
    private CheckBox acknowledgedCheckBox;
    @FXML
    private Button acknowledgeTaskButton;
    @FXML
    private Button refreshTasksButton;
    @FXML
    private Button backToDashboardButton;
    
    private List<AssignedTasks> allTasks = new ArrayList<>();
    private AssignedTasks selectedTask;
    private static final String ASSIGNED_TASKS_FILE = "assigned_tasks.bin";
    
    @FXML
    public void initialize() {
        setupTableColumns();
        setupComboBoxes();
        loadAssignedTasks();
        setupTableSelection();
        updateStatistics();
    }
    
    private void setupTableColumns() {
        taskIdCol.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        staffNameCol.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        taskTypeCol.setCellValueFactory(new PropertyValueFactory<>("taskType"));
        flightNumberCol.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        workloadCol.setCellValueFactory(new PropertyValueFactory<>("workload"));
        assignedByCol.setCellValueFactory(new PropertyValueFactory<>("assignedBy"));
    }
    
    private void setupComboBoxes() {
        filterStatusComboBox.getItems().addAll("All Status", "Pending", "In Progress", "Completed", "Acknowledged");
        filterPriorityComboBox.getItems().addAll("All Priority", "High", "Normal", "Low");
        
        filterStatusComboBox.setOnAction(event -> applyFilters());
        filterPriorityComboBox.setOnAction(event -> applyFilters());
    }
    
    private void setupTableSelection() {
        assignedTasksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedTask = newSelection;
                displayTaskDetails(selectedTask);
                acknowledgedCheckBox.setSelected(selectedTask.isCompleted());
            }
        });
    }
    
    private void loadAssignedTasks() {
        ObjectInputStream ois = null;
        try {
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            
            File file = new File(ASSIGNED_TASKS_FILE);
            if (!file.exists()) {
                createEmptyFile();
                return;
            }
            
            ois = new ObjectInputStream(new FileInputStream(ASSIGNED_TASKS_FILE));
            assignedTasksTable.getItems().clear();
            allTasks.clear();
            
            try {
                while (true) {
                    Object obj = ois.readObject();
                    if (obj instanceof AssignedTasks) {
                        AssignedTasks task = (AssignedTasks) obj;
                        allTasks.add(task);
                        assignedTasksTable.getItems().add(task);
                    } else if (obj instanceof java.util.List) {
                        java.util.List<?> list = (java.util.List<?>) obj;
                        for (Object item : list) {
                            if (item instanceof AssignedTasks) {
                                AssignedTasks task = (AssignedTasks) item;
                                allTasks.add(task);
                                assignedTasksTable.getItems().add(task);
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
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ASSIGNED_TASKS_FILE));
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void displayTaskDetails(AssignedTasks task) {
        StringBuilder details = new StringBuilder();
        details.append("=== TASK DETAILS ===\n\n");
        details.append("Task ID: ").append(task.getTaskId()).append("\n");
        details.append("Staff Name: ").append(task.getStaffName()).append("\n");
        details.append("Task Type: ").append(task.getTaskType()).append("\n");
        details.append("Flight Number: ").append(task.getFlightNumber()).append("\n");
        details.append("Due Date: ").append(task.getDueDate()).append("\n");
        details.append("Status: ").append(task.getStatus()).append("\n");
        details.append("Workload: ").append(task.getWorkload()).append(" hours\n");
        details.append("Assigned By: ").append(task.getAssignedBy()).append("\n");
        details.append("Assigned Date: ").append(task.getAssignedDate()).append("\n");
        details.append("Notes: ").append(task.getNotes()).append("\n");
        
        taskDetailsTextArea.setText(details.toString());
    }
    
    private void updateStatistics() {
        totalTasksLabel.setText("Total Tasks: " + allTasks.size());
        
        long pendingCount = allTasks.stream()
                .filter(task -> "Pending".equals(task.getStatus()))
                .count();
        pendingTasksLabel.setText("Pending: " + pendingCount);
        
        long acknowledgedCount = allTasks.stream()
                .filter(task -> "Acknowledged".equals(task.getStatus()))
                .count();
        acknowledgedTasksLabel.setText("Acknowledged: " + acknowledgedCount);
        
        long completedCount = allTasks.stream()
                .filter(task -> "Completed".equals(task.getStatus()))
                .count();
        completedTasksLabel.setText("Completed: " + completedCount);
    }
    
    private void applyFilters() {
        String selectedStatus = filterStatusComboBox.getValue();
        String selectedPriority = filterPriorityComboBox.getValue();
        
        List<AssignedTasks> filteredTasks = allTasks.stream()
                .filter(task -> selectedStatus == null || "All Status".equals(selectedStatus) || 
                        task.getStatus().equals(selectedStatus))
                .collect(Collectors.toList());
        
        assignedTasksTable.getItems().clear();
        assignedTasksTable.getItems().addAll(filteredTasks);
    }
    
    @FXML
    public void acknowledgeTaskButton(ActionEvent actionEvent) {
        if (selectedTask == null) {
            showAlert("Error", "Please select a task to acknowledge");
            return;
        }
        
        selectedTask.setStatus("Acknowledged");
        selectedTask.setCompleted(true);
        
        assignedTasksTable.refresh();
        updateStatistics();
        displayTaskDetails(selectedTask);
        
        saveAllTasks();
        showAlert("Success", "Task acknowledged successfully!");
    }
    
    @FXML
    public void refreshTasksButton(ActionEvent actionEvent) {
        loadAssignedTasks();
        updateStatistics();
        showAlert("Refresh Complete", "Tasks have been refreshed from the system!");
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
    
    @FXML
    public void clearFiltersButton(ActionEvent actionEvent) {
        filterStatusComboBox.setValue("All Status");
        filterPriorityComboBox.setValue("All Priority");
        assignedTasksTable.getItems().clear();
        assignedTasksTable.getItems().addAll(allTasks);
    }
    
    @FXML
    public void showUrgentTasksButton(ActionEvent actionEvent) {
        List<AssignedTasks> urgentTasks = allTasks.stream()
                .filter(task -> task.getDueDate().isBefore(LocalDateTime.now().plusHours(2)))
                .collect(Collectors.toList());
        
        if (urgentTasks.isEmpty()) {
            showAlert("Urgent Tasks", "No urgent tasks found. All tasks are within acceptable timeframes.");
        } else {
            StringBuilder urgentList = new StringBuilder();
            urgentList.append("=== URGENT TASKS (Due within 2 hours) ===\n\n");
            
            for (AssignedTasks task : urgentTasks) {
                urgentList.append("• ").append(task.getTaskName())
                         .append(" (Flight: ").append(task.getFlightNumber())
                         .append(", Due: ").append(task.getDueDate())
                         .append(")\n");
            }
            
            showAlert("Urgent Tasks", urgentList.toString());
        }
    }
    
    private void saveAllTasks() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ASSIGNED_TASKS_FILE));
            for (AssignedTasks task : allTasks) {
                oos.writeObject(task);
            }
            oos.close();
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
