package com.oop.groupeighteen.group18cateringservice.Lianna;

import com.oop.groupeighteen.group18cateringservice.Lianna.models.FlightUpdate;

import javafx.event.ActionEvent;
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

public class FlightUpdateDashboardcontroller {
    @javafx.fxml.FXML
    private TextField flightnoupdatefield;
    @javafx.fxml.FXML
    private TableColumn flightupdatecol;
    @javafx.fxml.FXML
    private TableColumn airlineupdatecol;
    @javafx.fxml.FXML
    private TextField departurefield;
    @javafx.fxml.FXML
    private TableView<FlightUpdate> flightupdatetable;
    @javafx.fxml.FXML
    private TextField arrivaltimefield;
    @javafx.fxml.FXML
    private TableColumn arrivaltimeupdatecol;
    @javafx.fxml.FXML
    private TextField airlinefield;
    @javafx.fxml.FXML
    private TableColumn departuretimeupdatecol;
    @javafx.fxml.FXML
    private TextField passengerCountField;
    @javafx.fxml.FXML
    private ComboBox<String> mealTypeComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> statusComboBox;
    @javafx.fxml.FXML
    private TextArea scheduleChangeTextArea;
    @javafx.fxml.FXML
    private TextArea cateringManagerNotesTextArea;
    @javafx.fxml.FXML
    private TextArea headChefResponseTextArea;
    @javafx.fxml.FXML
    private CheckBox confirmedCheckBox;
    @javafx.fxml.FXML
    private TableColumn<FlightUpdate, Integer> passengerCountCol;
    @javafx.fxml.FXML
    private TableColumn<FlightUpdate, String> mealTypeCol;
    @javafx.fxml.FXML
    private TableColumn<FlightUpdate, String> statusCol;
    @javafx.fxml.FXML
    private TableColumn<FlightUpdate, Boolean> confirmedCol;

    private List<FlightUpdate> flights = new ArrayList<>();
    private FlightUpdate selectedFlight;
    private static final String FLIGHTS_FILE = "flights.bin";

    @javafx.fxml.FXML
    public void initialize() {
        setupTableColumns();
        setupComboBoxes();
        loadFlights();
        setupTableSelection();
    }

    private void setupTableColumns() {
        flightupdatecol.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        airlineupdatecol.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
        departuretimeupdatecol.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrivaltimeupdatecol.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        passengerCountCol.setCellValueFactory(new PropertyValueFactory<>("passengerCount"));
        mealTypeCol.setCellValueFactory(new PropertyValueFactory<>("mealType"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        confirmedCol.setCellValueFactory(new PropertyValueFactory<>("confirmed"));
    }

    private void setupComboBoxes() {
        mealTypeComboBox.getItems().addAll("Breakfast", "Lunch", "Dinner", "Snack");
        statusComboBox.getItems().addAll("Scheduled", "Boarding", "In Flight", "Landed", "Delayed", "Cancelled", "Schedule Changed");
    }

    private void setupTableSelection() {
        flightupdatetable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedFlight = newSelection;
                loadFlightToForm(selectedFlight);
            }
        });
    }

    private void loadFlightToForm(FlightUpdate flight) {
        flightnoupdatefield.setText(flight.getFlightNumber());
        airlinefield.setText(flight.getAirlineName());
        departurefield.setText(flight.getDepartureTime());
        arrivaltimefield.setText(flight.getArrivalTime());
        passengerCountField.setText(String.valueOf(flight.getPassengerCount()));
        mealTypeComboBox.setValue(flight.getMealType());
        statusComboBox.setValue(flight.getStatus());
        scheduleChangeTextArea.setText(flight.getScheduleChange());
        cateringManagerNotesTextArea.setText(flight.getCateringManagerNotes());
        headChefResponseTextArea.setText(flight.getHeadChefResponse());
        confirmedCheckBox.setSelected(flight.isConfirmed());
    }

    private void loadFlights() {
        ObjectInputStream ois = null;
        try {
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }

            File file = new File(FLIGHTS_FILE);
            if (!file.exists()) {
                createEmptyFile();
                return;
            }

            ois = new ObjectInputStream(new FileInputStream(FLIGHTS_FILE));
            flightupdatetable.getItems().clear();
            flights.clear();

            try {
                while (true) {
                    Object obj = ois.readObject();
                    if (obj instanceof FlightUpdate) {
                        FlightUpdate flight = (FlightUpdate) obj;
                        flights.add(flight);
                        flightupdatetable.getItems().add(flight);
                    } else if (obj instanceof java.util.List) {
                        java.util.List<?> list = (java.util.List<?>) obj;
                        for (Object item : list) {
                            if (item instanceof FlightUpdate) {
                                FlightUpdate flight = (FlightUpdate) item;
                                flights.add(flight);
                                flightupdatetable.getItems().add(flight);
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
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FLIGHTS_FILE));
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void addflightbutton(ActionEvent actionEvent) {
        try {
            if (flightnoupdatefield.getText().isEmpty() || airlinefield.getText().isEmpty() ||
                    departurefield.getText().isEmpty() || arrivaltimefield.getText().isEmpty() ||
                    passengerCountField.getText().isEmpty()) {
                showAlert("Error", "Please fill in all required fields");
                return;
            }

            FlightUpdate newFlight = new FlightUpdate(
                    "FL" + System.currentTimeMillis(),
                    flightnoupdatefield.getText(),
                    airlinefield.getText(),
                    departurefield.getText(),
                    arrivaltimefield.getText(),
                    statusComboBox.getValue() != null ? statusComboBox.getValue() : "Scheduled",
                    Integer.parseInt(passengerCountField.getText()),
                    mealTypeComboBox.getValue() != null ? mealTypeComboBox.getValue() : "Lunch",
                    "Chef Lianna"
            );

            newFlight.setScheduleChange(scheduleChangeTextArea.getText());
            newFlight.setCateringManagerNotes(cateringManagerNotesTextArea.getText());
            newFlight.setHeadChefResponse(headChefResponseTextArea.getText());
            newFlight.setConfirmed(confirmedCheckBox.isSelected());

            flights.add(newFlight);
            flightupdatetable.getItems().add(newFlight);

            saveToBinaryFile(newFlight);
            showAlert("Success", "Flight schedule update added successfully!");
            clearForm();

        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid passenger count");
        } catch (Exception e) {
            showAlert("Error", "An error occurred while adding flight: " + e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void updateflightbutton(ActionEvent actionEvent) {
        if (selectedFlight == null) {
            showAlert("Error", "Please select a flight to update");
            return;
        }

        try {
            selectedFlight.setFlightNumber(flightnoupdatefield.getText());
            selectedFlight.setAirlineName(airlinefield.getText());
            selectedFlight.setDepartureTime(departurefield.getText());
            selectedFlight.setArrivalTime(arrivaltimefield.getText());
            selectedFlight.setPassengerCount(Integer.parseInt(passengerCountField.getText()));
            selectedFlight.setMealType(mealTypeComboBox.getValue());
            selectedFlight.setStatus(statusComboBox.getValue());
            selectedFlight.setScheduleChange(scheduleChangeTextArea.getText());
            selectedFlight.setCateringManagerNotes(cateringManagerNotesTextArea.getText());
            selectedFlight.setHeadChefResponse(headChefResponseTextArea.getText());
            selectedFlight.setConfirmed(confirmedCheckBox.isSelected());
            selectedFlight.setLastModified(LocalDateTime.now());

            flightupdatetable.refresh();
            saveAllFlights();
            showAlert("Success", "Flight schedule update completed successfully!");
            clearForm();

        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid passenger count");
        } catch (Exception e) {
            showAlert("Error", "An error occurred while updating flight: " + e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void removeflightbutton(ActionEvent actionEvent) {
        if (selectedFlight == null) {
            showAlert("Error", "Please select a flight to remove");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Removal");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to remove flight " + selectedFlight.getFlightNumber() + "?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                flights.remove(selectedFlight);
                flightupdatetable.getItems().remove(selectedFlight);
                saveAllFlights();
                showAlert("Success", "Flight removed successfully!");
                clearForm();
                selectedFlight = null;
            }
        });
    }

    @javafx.fxml.FXML
    public void saveflightupdatebutton(ActionEvent actionEvent) {
        saveAllFlights();
        showAlert("Success", "All flight schedule updates saved successfully!");
    }

    @javafx.fxml.FXML
    public void reportIssueButton(ActionEvent actionEvent) {
        if (selectedFlight == null) {
            showAlert("Error", "Please select a flight to report issue");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Report Issue to Catering Manager");
        dialog.setHeaderText("Flight: " + selectedFlight.getFlightNumber());
        dialog.setContentText("Please describe the issue or conflict:");

        dialog.showAndWait().ifPresent(issue -> {
            selectedFlight.setHeadChefResponse("ISSUE REPORTED: " + issue);
            selectedFlight.setLastModified(LocalDateTime.now());
            flightupdatetable.refresh();
            saveAllFlights();
            showAlert("Success", "Issue reported to Catering Manager for flight " + selectedFlight.getFlightNumber());
        });
    }

    @javafx.fxml.FXML
    public void confirmScheduleButton(ActionEvent actionEvent) {
        if (selectedFlight == null) {
            showAlert("Error", "Please select a flight to confirm schedule");
            return;
        }

        selectedFlight.setConfirmed(true);
        selectedFlight.setHeadChefResponse("Schedule confirmed with Catering Manager");
        selectedFlight.setLastModified(LocalDateTime.now());
        confirmedCheckBox.setSelected(true);
        flightupdatetable.refresh();
        saveAllFlights();
        showAlert("Success", "Schedule confirmed with Catering Manager for flight " + selectedFlight.getFlightNumber());
    }

    @javafx.fxml.FXML
    public void backfromflightupdatedashboardbutton(ActionEvent actionEvent) {
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

    private void saveToBinaryFile(FlightUpdate flight) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FLIGHTS_FILE, true)) {
                @Override
                protected void writeStreamHeader() throws IOException {
                }
            };

            oos.writeObject(flight);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAllFlights() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FLIGHTS_FILE));
            for (FlightUpdate flight : flights) {
                oos.writeObject(flight);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        flightnoupdatefield.clear();
        airlinefield.clear();
        departurefield.clear();
        arrivaltimefield.clear();
        passengerCountField.clear();
        mealTypeComboBox.setValue(null);
        statusComboBox.setValue(null);
        scheduleChangeTextArea.clear();
        cateringManagerNotesTextArea.clear();
        headChefResponseTextArea.clear();
        confirmedCheckBox.setSelected(false);
        selectedFlight = null;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
