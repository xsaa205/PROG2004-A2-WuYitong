package model;

/**
 * Part1要求：封装游乐设施的核心属性，包含1个Employee类型变量
 * 后续Part2-Part7将基于此类扩展方法
 */
public class Ride {
    // 3个核心属性（含1个Employee类型，满足Part1要求）
    private String rideId;          // 游乐设施ID（如"RC-001"）
    private String rideName;        // 游乐设施名称（如"超级过山车"）
    private Employee operator;      // 操作员（必须有操作员才能运行）

    // 1. 默认构造器（满足Part1要求）
    public Ride() {}

    // 2. 带参构造器：初始化所有属性
    public Ride(String rideId, String rideName, Employee operator) {
        this.rideId = rideId;
        this.rideName = rideName;
        this.operator = operator;
    }

    // 3. Getter与Setter（封装属性，满足Part1要求）
    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    // 4. 重写toString：便于打印设施详情
    @Override
    public String toString() {
        return "Ride{" +
                "rideId='" + rideId + '\'' +
                ", rideName='" + rideName + '\'' +
                ", operator=" + (operator != null ? operator.getFullName() : "未分配") +
                '}';
    }
}