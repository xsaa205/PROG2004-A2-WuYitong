package model;

/**
 * Part2要求：实现Person类的抽象方法introduceYourself()
 */
public class Employee extends Person {
    // Part1原有属性（不变）
    private String employeeRole;    // 岗位（如"过山车操作员"）
    private String assignedRideId;  // 负责的游乐设施ID（如"RC-001"）

    // Part1原有构造器（不变）
    public Employee() {}

    public Employee(String id, String fullName, int age, String employeeRole, String assignedRideId) {
        super(id, fullName, age);
        this.employeeRole = employeeRole;
        this.assignedRideId = assignedRideId;
    }

    // Part1原有Getter/Setter（不变）
    public String getEmployeeRole() { return employeeRole; }
    public void setEmployeeRole(String employeeRole) { this.employeeRole = employeeRole; }
    public String getAssignedRideId() { return assignedRideId; }
    public void setAssignedRideId(String assignedRideId) { this.assignedRideId = assignedRideId; }

    // -------------------------- Part2新增：实现抽象方法 --------------------------
    @Override
    public String introduceYourself() {
        return "【员工】" + getFullName() + "（ID：" + getId() + "），岗位：" + employeeRole + "，负责设施ID：" + assignedRideId;
    }

    // Part1原有toString（不变）
    @Override
    public String toString() {
        return "Employee{" +
                "employeeRole='" + employeeRole + '\'' +
                ", assignedRideId='" + assignedRideId + '\'' +
                "} " + super.toString();
    }
}