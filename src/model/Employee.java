package model;

/**
 * Part1要求：继承Person类，封装游乐设施操作员的专属属性与行为
 * 包含2个专属实例变量、默认/带参构造器、getter/setter
 */
public class Employee extends Person {
    // 2个专属实例变量（满足Part1"至少2个"要求）
    private String employeeRole;    // 岗位（如"过山车操作员"）
    private String assignedRideId;  // 负责的游乐设施ID（如"RC-001"）

    // 1. 默认构造器（满足Part1要求）
    public Employee() {}

    // 2. 带参构造器：初始化父类属性+子类属性（调用super()）
    public Employee(String id, String fullName, int age, String employeeRole, String assignedRideId) {
        super(id, fullName, age);  // 继承父类的id、姓名、年龄
        this.employeeRole = employeeRole;
        this.assignedRideId = assignedRideId;
    }

    // 3. Getter与Setter（封装专属属性，满足Part1要求）
    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getAssignedRideId() {
        return assignedRideId;
    }

    public void setAssignedRideId(String assignedRideId) {
        this.assignedRideId = assignedRideId;
    }

    // 4. 实现父类抽象方法：员工自我介绍（体现多态）
    @Override
    public String introduceYourself() {
        return "【员工】" + getFullName() + "，岗位：" + employeeRole + "，负责设施ID：" + assignedRideId;
    }

    // 5. 重写toString：包含父类+子类属性（打印更全面）
    @Override
    public String toString() {
        return "Employee{" +
                "employeeRole='" + employeeRole + '\'' +
                ", assignedRideId='" + assignedRideId + '\'' +
                "} " + super.toString();  // 调用父类toString，避免代码冗余
    }
}