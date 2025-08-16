package com.oop.groupeighteen.group18cateringservice.Lianna.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class FlightUpdate implements Serializable {
    private String flightId;
    private String flightNumber;
    private String airlineName;
    private String departureTime;
    private String arrivalTime;
    private String status;
    private int passengerCount;
    private String mealType;
    private String scheduleChange;
    private String cateringManagerNotes;
    private String headChefResponse;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModified;
    private String notes;
    private boolean isActive;
    private boolean isConfirmed;

    public FlightUpdate() {
        this.createdDate = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
        this.isActive = true;
        this.isConfirmed = false;
    }

    public FlightUpdate(String flightId, String flightNumber, String airlineName, String departureTime, 
                       String arrivalTime, String status, int passengerCount, String mealType, String createdBy) {
        this();
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.passengerCount = passengerCount;
        this.mealType = mealType;
        this.createdBy = createdBy;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getScheduleChange() {
        return scheduleChange;
    }

    public void setScheduleChange(String scheduleChange) {
        this.scheduleChange = scheduleChange;
    }

    public String getCateringManagerNotes() {
        return cateringManagerNotes;
    }

    public void setCateringManagerNotes(String cateringManagerNotes) {
        this.cateringManagerNotes = cateringManagerNotes;
    }

    public String getHeadChefResponse() {
        return headChefResponse;
    }

    public void setHeadChefResponse(String headChefResponse) {
        this.headChefResponse = headChefResponse;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    @Override
    public String toString() {
        return flightNumber + " - " + airlineName;
    }
}
