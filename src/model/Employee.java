package model;

@SuppressWarnings("unused") // Suppress unused method/constructor warnings
public class Employee extends Person {
    private String employeeRole;    // Role (e.g., "Roller Coaster Operator")
    private String assignedRideId;  // Assigned ride ID

    public Employee() {}

    public Employee(String id, String fullName, int age, String employeeRole, String assignedRideId) {
        super(id, fullName, age);
        this.employeeRole = employeeRole;
        this.assignedRideId = assignedRideId;
    }

    // Getters/Setters
    public String getEmployeeRole() { return employeeRole; }
    public void setEmployeeRole(String employeeRole) { this.employeeRole = employeeRole; }
    public String getAssignedRideId() { return assignedRideId; }
    public void setAssignedRideId(String assignedRideId) { this.assignedRideId = assignedRideId; }

    // Implement abstract method
    @Override
    public String introduceYourself() {
        return "[Employee] " + getFullName() + " (ID: " + getId() + "), Role: " + employeeRole + ", Assigned Ride ID: " + assignedRideId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeRole='" + employeeRole + '\'' +
                ", assignedRideId='" + assignedRideId + '\'' +
                "} " + super.toString();
    }
}