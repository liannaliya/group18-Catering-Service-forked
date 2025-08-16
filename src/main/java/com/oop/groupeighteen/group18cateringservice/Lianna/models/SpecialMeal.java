package com.oop.groupeighteen.group18cateringservice.Lianna.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SpecialMeal implements Serializable {
    private String requestId;
    private String passengerName;
    private String flightNumber;
    private String mealType;
    private String dietaryRequirement;
    private String specialInstructions;
    private String status;
    private String assignedStaff;
    private String preparationInstructions;
    private LocalDateTime requestDate;
    private LocalDateTime dueDate;
    private LocalDateTime completionDate;
    private String qualityNotes;
    private boolean isCompleted;
    private boolean isQualityVerified;
    private String createdBy;
    private LocalDateTime lastModified;

    public SpecialMeal() {
        this.requestDate = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
        this.status = "Pending";
        this.isCompleted = false;
        this.isQualityVerified = false;
    }

    public SpecialMeal(String requestId, String passengerName, String flightNumber, String mealType, 
                      String dietaryRequirement, String specialInstructions, LocalDateTime dueDate, String createdBy) {
        this();
        this.requestId = requestId;
        this.passengerName = passengerName;
        this.flightNumber = flightNumber;
        this.mealType = mealType;
        this.dietaryRequirement = dietaryRequirement;
        this.specialInstructions = specialInstructions;
        this.dueDate = dueDate;
        this.createdBy = createdBy;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
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

    public String getDietaryRequirement() {
        return dietaryRequirement;
    }

    public void setDietaryRequirement(String dietaryRequirement) {
        this.dietaryRequirement = dietaryRequirement;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedStaff() {
        return assignedStaff;
    }

    public void setAssignedStaff(String assignedStaff) {
        this.assignedStaff = assignedStaff;
    }

    public String getPreparationInstructions() {
        return preparationInstructions;
    }

    public void setPreparationInstructions(String preparationInstructions) {
        this.preparationInstructions = preparationInstructions;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public String getQualityNotes() {
        return qualityNotes;
    }

    public void setQualityNotes(String qualityNotes) {
        this.qualityNotes = qualityNotes;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isQualityVerified() {
        return isQualityVerified;
    }

    public void setQualityVerified(boolean qualityVerified) {
        isQualityVerified = qualityVerified;
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

    @Override
    public String toString() {
        return passengerName + " - " + mealType + " (" + status + ")";
    }
}
