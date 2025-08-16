package com.oop.groupeighteen.group18cateringservice.Lianna;

import com.oop.groupeighteen.group18cateringservice.Lianna.models.AssignedTasks;
import com.oop.groupeighteen.group18cateringservice.Lianna.models.StaffAssignment;

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
import java.util.Map;
import java.util.HashMap;

public class AssignedTasksDashboardcontroller {
    @javafx.fxml.FXML
    private TableColumn staffnameassignedtaskscol;
    @javafx.fxml.FXML
    private TableView<AssignedTasks> assignmenttable;
    @javafx.fxml.FXML
    private TextField flightNumberField;
    @javafx.fxml.FXML
    private TableColumn taskassignedcol;
    @javafx.fxml.FXML
    private TextField staffNameField;
    @javafx.fxml.FXML
    private ComboBox<String> taskTypeComboBox;
    @javafx.fxml.FXML
    private DatePicker dueDatePicker;
    @javafx.fxml.FXML
    private Spinner<Integer> workloadSpinner;
    @javafx.fxml.FXML
    private TextArea notesTextArea;
    @javafx.fxml.FXML
    private TableColumn<AssignedTasks, String> flightNumberCol;
    @javafx.fxml.FXML
    private TableColumn<AssignedTasks, LocalDateTime> dueDateCol;
    @javafx.fxml.FXML
    private TableColumn<AssignedTasks, String> statusCol;
    @javafx.fxml.FXML
    private TableColumn<AssignedTasks, Integer> workloadCol;
    @javafx.fxml.FXML
    private TableColumn<AssignedTasks, String> assignedByCol;

    private List<AssignedTasks> assignedTasks = new ArrayList<>();
    private AssignedTasks selectedTask;
    private static final String ASSIGNED_TASKS_FILE = "assigned_tasks.bin";

    @javafx.fxml.FXML
    public void initialize() {
        setupTableColumns();
        setupComboBoxes();
        setupSpinner();
        loadAssignedTasks();
        setupTableSelection();
    }

    private void setupTableColumns() {
        staffnameassignedtaskscol.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        taskassignedcol.setCellValueFactory(new PropertyValueFactory<>("taskType"));
        flightNumberCol.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        workloadCol.setCellValueFactory(new PropertyValueFactory<>("workload"));
        assignedByCol.setCellValueFactory(new PropertyValueFactory<>("assignedBy"));
    }

    private void setupComboBoxes() {
        taskTypeComboBox.getItems().addAll("Rice Preparation", "Meat Cooking", "Vegetable Prep", "Sauce Making", "Dessert Prep", "Plating", "Quality Check");
    }

    private void setupSpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 4);
        workloadSpinner.setValueFactory(valueFactory);
    }

    private void setupTableSelection() {
        assignmenttable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedTask = newSelection;
                loadTaskToForm(selectedTask);
            }
        });
    }

    private void loadTaskToForm(AssignedTasks task) {
        staffNameField.setText(task.getStaffName());
        taskTypeComboBox.setValue(task.getTaskType());
        flightNumberField.setText(task.getFlightNumber());
        dueDatePicker.setValue(task.getDueDate().toLocalDate());
        workloadSpinner.getValueFactory().setValue(task.getWorkload());
        notesTextArea.setText(task.getNotes());
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
            assignmenttable.getItems().clear();
            assignedTasks.clear();

            try {
                while (true) {
                    Object obj = ois.readObject();
                    if (obj instanceof AssignedTasks) {
                        AssignedTasks task = (AssignedTasks) obj;
                        assignedTasks.add(task);
                        assignmenttable.getItems().add(task);
                    } else if (obj instanceof java.util.List) {
                        java.util.List<?> list = (java.util.List<?>) obj;
                        for (Object item : list) {
                            if (item instanceof AssignedTasks) {
                                AssignedTasks task = (AssignedTasks) item;
                                assignedTasks.add(task);
                                assignmenttable.getItems().add(task);
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

    @javafx.fxml.FXML
    public void assignTaskButton(ActionEvent actionEvent) {
        try {
            if (staffNameField.getText().isEmpty() || taskTypeComboBox.getValue() == null ||
                    flightNumberField.getText().isEmpty() || dueDatePicker.getValue() == null) {
                showAlert("Error", "Please fill in all required fields");
                return;
            }

            AssignedTasks newTask = new AssignedTasks(
                    "TASK" + System.currentTimeMillis(),
                    staffNameField.getText(),
                    "ST" + System.currentTimeMillis(),
                    taskTypeComboBox.getValue(),
                    taskTypeComboBox.getValue(),
                    flightNumberField.getText(),
                    dueDatePicker.getValue().atStartOfDay(),
                    "Chef Lianna",
                    workloadSpinner.getValue()
            );

            newTask.setNotes(notesTextArea.getText());

            assignedTasks.add(newTask);
            assignmenttable.getItems().add(newTask);

            saveToBinaryFile(newTask);
            showAlert("Success", "Task assigned successfully to " + newTask.getStaffName());
            clearForm();

        } catch (Exception e) {
            showAlert("Error", "An error occurred while assigning task: " + e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void viewStaffAssignmentsButton(ActionEvent actionEvent) {
        if (assignedTasks.isEmpty()) {
            showAlert("Information", "No staff assignments found");
            return;
        }

        Map<String, List<AssignedTasks>> staffAssignments = new HashMap<>();
        for (AssignedTasks task : assignedTasks) {
            staffAssignments.computeIfAbsent(task.getStaffName(), k -> new ArrayList<>()).add(task);
        }

        StringBuilder report = new StringBuilder();
        report.append("Staff Assignment Report:\n\n");

        for (Map.Entry<String, List<AssignedTasks>> entry : staffAssignments.entrySet()) {
            report.append("Staff: ").append(entry.getKey()).append("\n");
            for (AssignedTasks task : entry.getValue()) {
                report.append("  - ").append(task.getTaskType())
                     .append(" (Flight: ").append(task.getFlightNumber())
                     .append(", Due: ").append(task.getDueDate().toLocalDate())
                     .append(", Status: ").append(task.getStatus())
                     .append(")\n");
            }
            report.append("\n");
        }

        showAlert("Staff Assignments", report.toString());
    }

    @javafx.fxml.FXML
    public void verifyWorkloadButton(ActionEvent actionEvent) {
        if (assignedTasks.isEmpty()) {
            showAlert("Information", "No assignments to verify");
            return;
        }

        Map<String, Integer> staffWorkload = new HashMap<>();
        for (AssignedTasks task : assignedTasks) {
            staffWorkload.merge(task.getStaffName(), task.getWorkload(), Integer::sum);
        }

        StringBuilder report = new StringBuilder();
        report.append("Workload Verification Report:\n\n");

        boolean hasOverload = false;
        for (Map.Entry<String, Integer> entry : staffWorkload.entrySet()) {
            String staffName = entry.getKey();
            int totalWorkload = entry.getValue();
            report.append(staffName).append(": ").append(totalWorkload).append(" hours");
            
            if (totalWorkload > 8) {
                report.append(" ⚠️ OVERLOADED");
                hasOverload = true;
            } else if (totalWorkload < 4) {
                report.append(" ⚠️ UNDERUTILIZED");
            } else {
                report.append(" ✅ OPTIMAL");
            }
            report.append("\n");
        }

        if (hasOverload) {
            report.append("\n⚠️ Some staff members are overloaded. Consider redistributing tasks.");
        }

        showAlert("Workload Verification", report.toString());
    }

    @javafx.fxml.FXML
    public void saveAssignmentPlanButton(ActionEvent actionEvent) {
        saveAllAssignments();
        showAlert("Success", "Assignment plan saved successfully!");
    }

    @javafx.fxml.FXML
    public void backfromassignedtaskbutton(ActionEvent actionEvent) {
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

    private void saveToBinaryFile(AssignedTasks task) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ASSIGNED_TASKS_FILE, true)) {
                @Override
                protected void writeStreamHeader() throws IOException {
                }
            };

            oos.writeObject(task);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAllAssignments() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ASSIGNED_TASKS_FILE));
            for (AssignedTasks task : assignedTasks) {
                oos.writeObject(task);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        staffNameField.clear();
        taskTypeComboBox.setValue(null);
        flightNumberField.clear();
        dueDatePicker.setValue(null);
        workloadSpinner.getValueFactory().setValue(4);
        notesTextArea.clear();
        selectedTask = null;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
