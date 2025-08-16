package com.oop.groupeighteen.group18cateringservice.Lianna.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AssignedTasks implements Serializable {
    private String taskId;
    private String staffName;
    private String taskName;
    private String flightNumber;
    private String status;
    private LocalDateTime dueDate;
    private String notes;
    private boolean completed;

    public AssignedTasks() {
        this.status = "Assigned";
    }

    public AssignedTasks(String taskId, String staffName, String taskName, 
                        String flightNumber, LocalDateTime dueDate) {
        this();
        this.taskId = taskId;
        this.staffName = staffName;
        this.taskName = taskName;
        this.flightNumber = flightNumber;
        this.dueDate = dueDate;
    }

    public AssignedTasks(String taskId, String staffName, String taskName, String taskType, 
                        String flightNumber, String notes, LocalDateTime dueDate, String assignedBy, Integer workload) {
        this();
        this.taskId = taskId;
        this.staffName = staffName;
        this.taskName = taskName;
        this.flightNumber = flightNumber;
        this.dueDate = dueDate;
        this.notes = notes;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return staffName + " - " + taskName + " (" + status + ")";
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTaskType() {
        return taskName;
    }

    public Integer getWorkload() {
        return 4;
    }

    public String getAssignedBy() {
        return "Chef Lianna";
    }

    public String getAssignedDate() {
        return dueDate != null ? dueDate.toLocalDate().toString() : "";
    }
}
