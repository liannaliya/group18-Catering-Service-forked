package com.oop.groupeighteen.group18cateringservice.Maliha;

import java.io.Serializable;
import java.time.LocalDate;

public class VehicleSchedule implements Serializable {
    private String scheduleId;
    private String vehicleId;
    private LocalDate scheduleDate;
    private String scheduleTime;
    private String driver;

    public VehicleSchedule(String scheduleId, String vehicleId, LocalDate scheduleDate, String scheduleTime, String driver) {
        this.scheduleId = scheduleId;
        this.vehicleId = vehicleId;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
        this.driver = driver;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "VehicleSchedule{" +
                "scheduleId='" + scheduleId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", scheduleDate=" + scheduleDate +
                ", scheduleTime=" + scheduleTime +
                ", driver='" + driver + '\'' +
                '}';
    }
}
