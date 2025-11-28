package model;

import util.RideInterface;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Part2要求：实现RideInterface接口，扩展Part1的Ride类
 * 新增队列、历史、运行相关属性，重写接口8个方法（暂留空实现，后续Part3-Part5补全）
 */
public class Ride implements RideInterface {
    // Part1原有属性（不变）
    private String rideId;          // 游乐设施ID（如"RC-001"）
    private String rideName;        // 游乐设施名称（如"超级过山车"）
    private Employee operator;      // 操作员（必须有操作员才能运行）

    // -------------------------- Part2新增属性（为后续功能铺路） --------------------------
    private Queue<Visitor> waitingQueue;    // Part3：排队队列（FIFO）
    private LinkedList<Visitor> rideHistory; // Part4A：游玩历史（支持Iterator）
    private int maxRider;           // Part5：单次运行最大载客量（至少1人）
    private int numOfCycles;        // Part5：运行次数（默认0）

    // -------------------------- Part2修改构造器（初始化新增属性） --------------------------
    public Ride() {
        // 空构造器：初始化集合（避免空指针）
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
        this.maxRider = 1; // 默认最大载客1人
    }

    // 带参构造器（推荐使用，初始化所有核心属性）
    public Ride(String rideId, String rideName, Employee operator, int maxRider) {
        this.rideId = rideId;
        this.rideName = rideName;
        this.operator = operator;
        // 载客量校验：至少1人，非法值默认1
        this.maxRider = (maxRider >= 1) ? maxRider : 1;

        // 初始化集合（关键：避免后续调用方法时空指针）
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // -------------------------- Part2新增Getter/Setter（非集合属性） --------------------------
    public String getRideId() { return rideId; }
    public void setRideId(String rideId) { this.rideId = rideId; }
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = (maxRider >= 1) ? maxRider : 1; }
    public int getNumOfCycles() { return numOfCycles; }

    // -------------------------- Part2：重写RideInterface接口的8个方法（空实现） --------------------------
    @Override
    public void addVisitorToQueue(Visitor visitor) {}

    @Override
    public void removeVisitorFromQueue() {}

    @Override
    public void printQueue() {}

    @Override
    public void addVisitorToHistory(Visitor visitor) {}

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) { return false; }

    @Override
    public int numberOfVisitors() { return 0; }

    @Override
    public void printRideHistory() {}

    @Override
    public void runOneCycle() {}

    // -------------------------- Part2修改toString（包含新增属性） --------------------------
    @Override
    public String toString() {
        return "Ride{" +
                "rideId='" + rideId + '\'' +
                ", rideName='" + rideName + '\'' +
                ", operator=" + (operator != null ? operator.getFullName() : "未分配") +
                ", maxRider=" + maxRider +
                ", 已运行次数=" + numOfCycles +
                ", 排队人数=" + waitingQueue.size() +
                ", 历史游玩人数=" + rideHistory.size() +
                '}';
    }
}