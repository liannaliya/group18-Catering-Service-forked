package com.oop.groupeighteen.group18cateringservice.Maliha;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Invoice2 implements Serializable {
    private Integer invoiceNumber;
    private String customerName;
    private Integer phoneNumber;
    private String address;
    private LocalDate date;
    private List<Product> products; // product list

    public Invoice2(Integer invoiceNumber, String customerName, Integer phoneNumber,
                    String address, LocalDate date, List<Product> products) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.date = date;
        this.products = products;
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Invoice2{" +
                "invoiceNumber=" + invoiceNumber +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", products=" + products +
                '}';
    }

    public float getTotalInvoicePrice() {
        float sum = 0;
        for (Product p : products) {
            sum += p.getTotalPrice();
        }
        return sum;
    }

}
