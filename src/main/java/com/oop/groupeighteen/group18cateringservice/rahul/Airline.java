package com.oop.groupeighteen.group18cateringservice.rahul;

import com.oop.groupeighteen.group18cateringservice.User;

import java.time.LocalDate;
import java.util.Random;

public class Airline extends User {
    private String cateringTerms;

    public Airline(String name, String phoneNo, String email, String address, String gender, String password, LocalDate dob) {
        super(name, phoneNo, email, address, gender, password, dob);
        this.setId(this.genarateID());
        this.cateringTerms = cateringTerms;
    }

    public String getCateringTerms() {
        return cateringTerms;
    }

    public void setCateringTerms(String cateringTerms) {
        this.cateringTerms = cateringTerms;
    }

    @Override
    public String toString() {
        return
                super.toString() + '\'' +
                "cateringTerms='" + cateringTerms + '\'' +
                '}';
    }

    @Override
    public String genarateID() {
        String id ="";

        Random random = new Random();
        id =Integer.toString(random.nextInt(100000,999999));

        return id;
        
    }

    
}
