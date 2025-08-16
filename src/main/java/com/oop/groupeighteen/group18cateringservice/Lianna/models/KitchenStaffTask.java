package com.oop.groupeighteen.group18cateringservice.Lianna.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class KitchenStaffTask implements Serializable {
    private String taskId;
    private String staffName;
    private String staffId;
    private String taskName;
    private String taskType;
    private String flightNumber;
    private String mealType;
    private String mealName;
    private String status;
    private LocalDateTime assignedDate;
    private LocalDateTime dueDate;
    private String assignedBy;
    private String notes;
    private boolean isAcknowledged;
    private boolean isCompleted;
    private int workload;
    private String priority;
    private String specialInstructions;
    private LocalDateTime acknowledgmentDate;
    private LocalDateTime completionDate;

    public KitchenStaffTask() {
        this.assignedDate = LocalDateTime.now();
        this.isAcknowledged = false;
        this.isCompleted = false;
        this.status = "Pending";
        this.priority = "Normal";
    }

    public KitchenStaffTask(String taskId, String staffName, String staffId, String taskName, 
                           String taskType, String flightNumber, String mealType, String mealName, 
                           LocalDateTime dueDate, String assignedBy, int workload) {
        this();
        this.taskId = taskId;
        this.staffName = staffName;
        this.staffId = staffId;
        this.taskName = taskName;
        this.taskType = taskType;
        this.flightNumber = flightNumber;
        this.mealType = mealType;
        this.mealName = mealName;
        this.dueDate = dueDate;
        this.assignedBy = assignedBy;
        this.workload = workload;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isAcknowledged() {
        return isAcknowledged;
    }

    public void setAcknowledged(boolean acknowledged) {
        isAcknowledged = acknowledged;
        if (acknowledged) {
            this.acknowledgmentDate = LocalDateTime.now();
        }
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
        if (completed) {
            this.completionDate = LocalDateTime.now();
        }
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public LocalDateTime getAcknowledgmentDate() {
        return acknowledgmentDate;
    }

    public void setAcknowledgmentDate(LocalDateTime acknowledgmentDate) {
        this.acknowledgmentDate = acknowledgmentDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public String toString() {
        return taskName + " - " + flightNumber + " (" + status + ")";
    }
}
