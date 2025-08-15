package com.oop.groupeighteen.group18cateringservice.Maliha;


public class Vehicle {
    private String vehicleId;
    private int capacity;

    public Vehicle(String vehicleId,  int capacity) {
        this.vehicleId = vehicleId;
        this.capacity = capacity;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
