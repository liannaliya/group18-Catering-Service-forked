package com.oop.groupeighteen.group18cateringservice.Lianna;

import javafx.scene.control.Button;

public class HeadChef {

    private Button menuplanbutton;
    private Button assignedtaskbutton;
    private Button kitchenprogressbutton;
    private Button ingredientsinventorbutton;
    private Button flightupdatebutton;
    private Button specialmealbutton;
    private Button qualitycheckbutton;
    private Button reviewperformancebutton;


    public HeadChef(Button menuplanbutton, Button assignedtaskbutton, Button kitchenprogressbutton, Button ingredientsinventorbutton, Button flightupdatebutton, Button specialmealbutton, Button qualitycheckbutton, Button reviewperformancebutton) {
        this.menuplanbutton = menuplanbutton;
        this.assignedtaskbutton = assignedtaskbutton;
        this.kitchenprogressbutton = kitchenprogressbutton;
        this.ingredientsinventorbutton = ingredientsinventorbutton;
        this.flightupdatebutton = flightupdatebutton;
        this.specialmealbutton = specialmealbutton;
        this.qualitycheckbutton = qualitycheckbutton;
        this.reviewperformancebutton = reviewperformancebutton;
    }

    public Button getMenuplanbutton() {
        return menuplanbutton;
    }

    public void setMenuplanbutton(Button menuplanbutton) {
        this.menuplanbutton = menuplanbutton;
    }

    public Button getAssignedtaskbutton() {
        return assignedtaskbutton;
    }

    public void setAssignedtaskbutton(Button assignedtaskbutton) {
        this.assignedtaskbutton = assignedtaskbutton;
    }

    public Button getKitchenprogressbutton() {
        return kitchenprogressbutton;
    }

    public void setKitchenprogressbutton(Button kitchenprogressbutton) {
        this.kitchenprogressbutton = kitchenprogressbutton;
    }

    public Button getIngredientsinventorbutton() {
        return ingredientsinventorbutton;
    }

    public void setIngredientsinventorbutton(Button ingredientsinventorbutton) {
        this.ingredientsinventorbutton = ingredientsinventorbutton;
    }

    public Button getFlightupdatebutton() {
        return flightupdatebutton;
    }

    public void setFlightupdatebutton(Button flightupdatebutton) {
        this.flightupdatebutton = flightupdatebutton;
    }

    public Button getSpecialmealbutton() {
        return specialmealbutton;
    }

    public void setSpecialmealbutton(Button specialmealbutton) {
        this.specialmealbutton = specialmealbutton;
    }

    public Button getQualitycheckbutton() {
        return qualitycheckbutton;
    }

    public void setQualitycheckbutton(Button qualitycheckbutton) {
        this.qualitycheckbutton = qualitycheckbutton;
    }

    public Button getReviewperformancebutton() {
        return reviewperformancebutton;
    }

    public void setReviewperformancebutton(Button reviewperformancebutton) {
        this.reviewperformancebutton = reviewperformancebutton;
    }

    @Override
    public String toString() {
        return "HeadChef{" +
                "menuplanbutton=" + menuplanbutton +
                ", assignedtaskbutton=" + assignedtaskbutton +
                ", kitchenprogressbutton=" + kitchenprogressbutton +
                ", ingredientsinventorbutton=" + ingredientsinventorbutton +
                ", flightupdatebutton=" + flightupdatebutton +
                ", specialmealbutton=" + specialmealbutton +
                ", qualitycheckbutton=" + qualitycheckbutton +
                ", reviewperformancebutton=" + reviewperformancebutton +
                '}';
    }
}
