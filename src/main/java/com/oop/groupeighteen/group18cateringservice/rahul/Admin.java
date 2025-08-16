package com.oop.groupeighteen.group18cateringservice.rahul;

import com.oop.groupeighteen.group18cateringservice.User;

import java.time.LocalDate;

public abstract class Admin extends User {
    public Admin(String name, String phoneNo, String email, String address, String gender, String password, LocalDate dob) {
        super(name, phoneNo, email, address, gender, password, dob);
    }
    public String generateID() {
        return "a";
    }

}
