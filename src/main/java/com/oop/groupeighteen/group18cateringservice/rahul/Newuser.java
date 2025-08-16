package com.oop.groupeighteen.group18cateringservice.rahul;

public class Newuser {
    String name, phone , email;

    public Newuser(String name, String phone, String email) {
        this.name = name;
        this.phone = this.phone;
        this.email = this.email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Newuser{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
