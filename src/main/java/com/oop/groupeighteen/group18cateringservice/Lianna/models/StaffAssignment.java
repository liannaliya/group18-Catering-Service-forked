package com.oop.groupeighteen.group18cateringservice.Lianna.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StaffAssignment implements Serializable {
    private String assignmentId;
    private String staffName;
    private String staffId;
    private String taskName;
    private String taskType;
    private String status;
    private LocalDateTime assignedDate;
    private LocalDateTime dueDate;
    private String assignedBy;
    private String notes;
    private boolean isCompleted;

    public StaffAssignment() {
        this.assignedDate = LocalDateTime.now();
        this.isCompleted = false;
    }

    public StaffAssignment(String assignmentId, String staffName, String staffId, String taskName, 
                          String taskType, String status, LocalDateTime dueDate, String assignedBy) {
        this();
        this.assignmentId = assignmentId;
        this.staffName = staffName;
        this.staffId = staffId;
        this.taskName = taskName;
        this.taskType = taskType;
        this.status = status;
        this.dueDate = dueDate;
        this.assignedBy = assignedBy;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return staffName + " - " + taskName + " (" + status + ")";
    }
}
