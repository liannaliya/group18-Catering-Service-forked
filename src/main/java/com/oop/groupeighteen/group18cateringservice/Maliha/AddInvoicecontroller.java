package com.oop.groupeighteen.group18cateringservice.Maliha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddInvoicecontroller {

    @javafx.fxml.FXML
    private TableColumn<Product, Integer> idCol;
    @javafx.fxml.FXML
    private TableColumn<Product, String> productnamecol;
    @javafx.fxml.FXML
    private TableColumn<Product, Integer> qtycol;
    @javafx.fxml.FXML
    private TableColumn<Product, Float> pricecol;
    @javafx.fxml.FXML
    private TableColumn<Product, Float> totalcol;
    @javafx.fxml.FXML
    private TableView<Product> tableview;

    @javafx.fxml.FXML
    private TextField invoicenumtextfield, cnameTextField, phnnumtextfield, addresstextfield;
    @javafx.fxml.FXML
    private DatePicker datepicker;
    @javafx.fxml.FXML
    private TextField productnametextfield, qtytextfield, pricetextfield;
    @javafx.fxml.FXML
    private Label totaltextfield;

    ObservableList<Product> productList = FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private TableColumn editcol;
    @javafx.fxml.FXML
    private TableColumn deletecol;

    @javafx.fxml.FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productnamecol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        qtycol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        totalcol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        tableview.setItems(productList);

        qtytextfield.textProperty().addListener((obs, oldVal, newVal) -> updateTotal());
        pricetextfield.textProperty().addListener((obs, oldVal, newVal) -> updateTotal());

        // ===== EDIT BUTTON =====
        editcol.setCellFactory(col -> new TableCell<Product, Void>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(e -> {
                    Product selectedProduct = getTableView().getItems().get(getIndex());
                    // Fill the form fields
                    productnametextfield.setText(selectedProduct.getProductName());
                    qtytextfield.setText(String.valueOf(selectedProduct.getQuantity()));
                    pricetextfield.setText(String.valueOf(selectedProduct.getUnitPrice()));
                    totaltextfield.setText(String.format("%.2f", selectedProduct.getTotalPrice()));

                    // Remove old item so updated one can be re-added
                    productList.remove(selectedProduct);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : editButton);
            }
        });

// ===== DELETE BUTTON =====
        deletecol.setCellFactory(col -> new TableCell<Product, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(e -> {
                    Product selectedProduct = getTableView().getItems().get(getIndex());
                    productList.remove(selectedProduct);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteButton);
            }
        });


    }

    // ADD PRODUCT WITH VALIDATION
    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        String productName = productnametextfield.getText().trim();
        String qtyText = qtytextfield.getText().trim();
        String priceText = pricetextfield.getText().trim();

        if (productName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Product name cannot be empty.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(qtyText);
            if (quantity <= 0) {
                showAlert(Alert.AlertType.ERROR, "Quantity must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Please enter a valid integer for quantity.");
            return;
        }

        float price;
        try {
            price = Float.parseFloat(priceText);
            if (price <= 0) {
                showAlert(Alert.AlertType.ERROR, "Price must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Please enter a valid number for price.");
            return;
        }

        // Generate random product ID
        int productId = (int) (Math.random() * 1000);

        Product product = new Product(productId, productName, quantity, price);
        productList.add(product);

        productnametextfield.clear();
        qtytextfield.clear();
        pricetextfield.clear();
        totaltextfield.setText("0.00");
    }

    // UPDATE TOTAL LABEL WHEN QTY OR PRICE CHANGES
    private void updateTotal() {
        try {
            int quantity = Integer.parseInt(qtytextfield.getText().trim());
            float price = Float.parseFloat(pricetextfield.getText().trim());
            if (quantity > 0 && price > 0) {
                totaltextfield.setText(String.format("%.2f", quantity * price));
            } else {
                totaltextfield.setText("0.00");
            }
        } catch (NumberFormatException e) {
            totaltextfield.setText("0.00");
        }
    }

    // SAVE INVOICE WITH FIELD VALIDATION
    @javafx.fxml.FXML
    public void handleSaveInvoice(ActionEvent actionEvent) {
        String invoiceText = invoicenumtextfield.getText().trim();
        String customerName = cnameTextField.getText().trim();
        String phoneText = phnnumtextfield.getText().trim();
        String address = addresstextfield.getText().trim();
        LocalDate date = datepicker.getValue();

        int invoiceNum;
        try {
            invoiceNum = Integer.parseInt(invoiceText);
            if (invoiceNum <= 0) {
                showAlert(Alert.AlertType.ERROR, "Invoice number must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Please enter a valid invoice number.");
            return;
        }

        if (customerName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Customer name cannot be empty.");
            return;
        }

        int phoneNumber;
        try {
            phoneNumber = Integer.parseInt(phoneText);
            if (phoneText.length() < 7 || phoneText.length() > 15) {
                showAlert(Alert.AlertType.ERROR, "Phone number must be between 7 and 15 digits.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Please enter a valid phone number.");
            return;
        }

        if (address.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Address cannot be empty.");
            return;
        }

        if (date == null) {
            showAlert(Alert.AlertType.ERROR, "Please select a date.");
            return;
        }

        if (productList.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Please add at least one product to the invoice.");
            return;
        }

        Invoice2 invoice = new Invoice2(invoiceNum, customerName, phoneNumber, address, date,
                new ArrayList<>(productList));

        File f = new File("Invoices.bin");
        try (FileOutputStream fos = new FileOutputStream(f, f.exists());
             ObjectOutputStream oos = f.exists()
                     ? new AppendableObjectOutputStream(fos)
                     : new ObjectOutputStream(fos)) {
            oos.writeObject(invoice);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        // Reset form after save
        invoicenumtextfield.clear();
        cnameTextField.clear();
        phnnumtextfield.clear();
        addresstextfield.clear();
        datepicker.setValue(null);
        productList.clear();
        totaltextfield.setText("0.00");

        showAlert(Alert.AlertType.INFORMATION, "Invoice saved successfully!");
    }

    // SIMPLE ALERT METHOD
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message);
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void handlePrintInvoice(ActionEvent actionEvent) {
//        try (PDDocument document = new PDDocument()) {
//            PDPage page = new PDPage();
//            document.addPage(page);
//
//            PDPageContentStream contentStream = new PDPageContentStream(document, page);
//            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
//            contentStream.beginText();
//            contentStream.newLineAtOffset(50, 750);
//            contentStream.showText("Invoice");
//            contentStream.newLineAtOffset(0, -20);
//            contentStream.setFont(PDType1Font.HELVETICA, 12);
//            contentStream.showText("Customer: " + cnameTextField.getText());
//            contentStream.newLineAtOffset(0, -20);
//            contentStream.showText("Phone: " + phnnumtextfield.getText());
//            contentStream.endText();
//            contentStream.close();
//
//            document.save("Invoice.pdf");
//            System.out.println("Invoice saved as Invoice.pdf");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @javafx.fxml.FXML
    public void handleSeeInvoices(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View Invoice.fxml"));
            Parent seeInvoicesRoot = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(seeInvoicesRoot);
            stage.setScene(scene);
            stage.setTitle("View Invoices");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load see_invoices.fxml");
        }
    }
}
