package com.oop.groupeighteen.group18cateringservice.Maliha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class VehicleManagercontroller {

    @javafx.fxml.FXML
    private DatePicker date;
    @javafx.fxml.FXML
    private TextField scheduleIdField;
    @javafx.fxml.FXML
    private TableView<VehicleSchedule> vehicletableview;
    @javafx.fxml.FXML
    private TableColumn<VehicleSchedule, String> idcol;
    @javafx.fxml.FXML
    private TableColumn<VehicleSchedule, LocalDate> dateCol;
    @javafx.fxml.FXML
    private ComboBox<String> vehicleCombo;
    @javafx.fxml.FXML
    private TableColumn<VehicleSchedule, String> vehicleCol;
    @javafx.fxml.FXML
    private TableColumn<VehicleSchedule, String> driverCol;
    @javafx.fxml.FXML
    private TableColumn<VehicleSchedule, String> timeCol;
    @javafx.fxml.FXML
    private TextField timeField;
    @javafx.fxml.FXML
    private ComboBox<String> driverCombo;

    private VehicleSchedule selectedSchedule = null; // store selected schedule
    ObservableList<VehicleSchedule> vehicleList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        // Link table columns
        idcol.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("scheduleDate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("scheduleTime"));
        vehicleCol.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        driverCol.setCellValueFactory(new PropertyValueFactory<>("driver"));

        // Combo data
        vehicleCombo.setItems(FXCollections.observableArrayList("V001", "V002", "V003"));
        driverCombo.setItems(FXCollections.observableArrayList("John Doe", "Jane Smith", "Alex Khan"));

        // Load existing data from file
        File f = new File("VehicleSchedules.bin");
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                while (true) {
                    try {
                        VehicleSchedule vs = (VehicleSchedule) ois.readObject();
                        vehicleList.add(vs);
                    } catch (EOFException eof) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        vehicletableview.setItems(vehicleList);

        // Handle double click on row → load into input fields
        vehicletableview.setRowFactory(tv -> {
            TableRow<VehicleSchedule> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    selectedSchedule = row.getItem();
                    scheduleIdField.setText(selectedSchedule.getScheduleId());
                    date.setValue(selectedSchedule.getScheduleDate());
                    timeField.setText(selectedSchedule.getScheduleTime());
                    vehicleCombo.setValue(selectedSchedule.getVehicleId());
                    driverCombo.setValue(selectedSchedule.getDriver());
                }
            });
            return row;
        });
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        String scheduleId = scheduleIdField.getText();
        String vehicleId = vehicleCombo.getValue();
        LocalDate scheduleDate = date.getValue();
        String scheduleTime = timeField.getText();
        String driver = driverCombo.getValue();

        VehicleSchedule vehicleSchedule = new VehicleSchedule(
                scheduleId, vehicleId, scheduleDate, scheduleTime, driver
        );

        // Save to file
        File f = new File("VehicleSchedules.bin");
        try (FileOutputStream fos = new FileOutputStream(f, true);
             ObjectOutputStream oos = f.exists() && f.length() > 0
                     ? new AppendableObjectOutputStream(fos)
                     : new ObjectOutputStream(fos)) {
            oos.writeObject(vehicleSchedule);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        vehicleList.add(vehicleSchedule);

        clearFields();
    }

    @javafx.fxml.FXML
    public void updateOnAction(ActionEvent actionEvent) {
        if (selectedSchedule != null) {
            // Update the selected object
            selectedSchedule.setScheduleId(scheduleIdField.getText());
            selectedSchedule.setVehicleId(vehicleCombo.getValue());
            selectedSchedule.setScheduleDate(date.getValue());
            selectedSchedule.setScheduleTime(timeField.getText());
            selectedSchedule.setDriver(driverCombo.getValue());

            vehicletableview.refresh(); // refresh table

            // Save updated list to file
            saveAllToFile();

            clearFields();
            selectedSchedule = null;
        }
    }

    private void saveAllToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("VehicleSchedules.bin"))) {
            for (VehicleSchedule vs : vehicleList) {
                oos.writeObject(vs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        scheduleIdField.clear();
        vehicleCombo.getSelectionModel().clearSelection();
        date.setValue(null);
        timeField.clear();
        driverCombo.getSelectionModel().clearSelection();
    }
}
