package com.oop.groupeighteen.group18cateringservice.Maliha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class ViewInvoiceController {
    @javafx.fxml.FXML
    private Label address;
    @javafx.fxml.FXML
    private TableColumn<Product, Integer> qtycol;
    @javafx.fxml.FXML
    private TableView<Product> productTable;
    @javafx.fxml.FXML
    private TableColumn<Product, Integer> productIdCol;
    @javafx.fxml.FXML
    private Label customerName;
    @javafx.fxml.FXML
    private Label Date;
    @javafx.fxml.FXML
    private TableColumn<Product, String> productNamecol;
    @javafx.fxml.FXML
    private TableColumn<Product, Float> unitPriceCol;
    @javafx.fxml.FXML
    private TableView<Invoice2> invoiceTable;
    @javafx.fxml.FXML
    private TableColumn<Product, Float> productTotalPriceCol;
    @javafx.fxml.FXML
    private TableColumn<Invoice2, String> datecol;
    @javafx.fxml.FXML
    private Label phone;
    @javafx.fxml.FXML
    private TableColumn<Invoice2, String> customerNameCol;
    @javafx.fxml.FXML
    private TableColumn<Invoice2, Float> totalPriceCol;
    @javafx.fxml.FXML
    private TableColumn<Invoice2, String> phoneNumberCol;
    @javafx.fxml.FXML
    private Label invoiceNo;

    private ObservableList<Invoice2> invoiceList = FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private TableColumn invoiceIdCol;

    @javafx.fxml.FXML
    public void initialize() throws IOException {

        // Invoice Table Columns
        invoiceIdCol.setCellValueFactory(new PropertyValueFactory<>("invoiceNumber"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalInvoicePrice")); // Ensure getter exists in Invoice2

//       product table column
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNamecol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        qtycol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        productTotalPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));


        File file = new File("Invoices.bin");
        if (file.exists() && file.length() > 0) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            System.out.println("inside try");
            try {
                while (true) {
                    Invoice2 invoice = (Invoice2) ois.readObject();
                    invoiceList.add(invoice);
                }
            } catch (EOFException e) {
                // End of file reached, normal
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Bind the list to the table
        invoiceTable.setItems(invoiceList);


        invoiceTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Invoice2 selectedInvoice = newSelection;
                invoiceNo.setText(String.valueOf(selectedInvoice.getInvoiceNumber()));
                customerName.setText(selectedInvoice.getCustomerName());
                phone.setText(String.valueOf(selectedInvoice.getPhoneNumber()));
                address.setText(selectedInvoice.getAddress());
                Date.setText(String.valueOf(selectedInvoice.getDate()));

                ObservableList<Product> products = FXCollections.observableArrayList(selectedInvoice.getProducts());
                productTable.setItems(products);
            }
        });
    }

    @javafx.fxml.FXML
    public void gotToAddNewInvoice(ActionEvent actionEvent) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("AddInvoice.fxml"));
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = (javafx.stage.Stage) invoiceTable.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
