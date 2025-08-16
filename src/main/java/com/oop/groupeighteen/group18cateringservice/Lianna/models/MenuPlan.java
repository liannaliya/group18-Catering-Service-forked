package com.oop.groupeighteen.group18cateringservice.Lianna.models;

import java.io.Serializable;
import java.time.LocalDate;

public class MenuPlan implements Serializable {
    private String planId;
    private String flightNumber;
    private String mealType;
    private String mealName;
    private int passengerCount;
    private String status;
    private LocalDate flightDate;

    public MenuPlan() {
    }

    public MenuPlan(String planId, String flightNumber, String mealType, String mealName, 
                   int passengerCount, String status, LocalDate flightDate) {
        this.planId = planId;
        this.flightNumber = flightNumber;
        this.mealType = mealType;
        this.mealName = mealName;
        this.passengerCount = passengerCount;
        this.status = status;
        this.flightDate = flightDate;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    @Override
    public String toString() {
        return flightNumber + " - " + mealName + " (" + status + ")";
    }
}
