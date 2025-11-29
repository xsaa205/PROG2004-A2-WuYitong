package model;

import util.RideInterface;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Part3要求：长隆欢乐世界 - 垂直过山车排队功能实现
 */
public class Ride implements RideInterface {
    // Part2原有属性（主题适配修改）
    private String rideId;          // 游乐设施ID（长隆编码规则）
    private String rideName;        // 游乐设施名称（长隆垂直过山车）
    private Employee operator;      // 长隆专项操作员

    // Part2新增属性（不变）
    private Queue<Visitor> waitingQueue;    // 排队队列（FIFO）
    private LinkedList<Visitor> rideHistory; // 游玩历史
    private int maxRider;           // 单次最大载客量（垂直过山车实际约24人，此处简化为8人）
    private int numOfCycles;        // 运行次数

    // Part2构造器（主题适配修改）
    public Ride() {
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
        this.maxRider = 8; // 垂直过山车简化载客量
    }

    public Ride(String rideId, String rideName, Employee operator, int maxRider) {
        this.rideId = rideId;
        this.rideName = rideName;
        this.operator = operator;
        this.maxRider = (maxRider >= 1) ? maxRider : 8; // 默认8人，符合大型过山车设定

        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // Getter/Setter（不变）
    public String getRideId() { return rideId; }
    public void setRideId(String rideId) { this.rideId = rideId; }
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = (maxRider >= 1) ? maxRider : 8; }
    public int getNumOfCycles() { return numOfCycles; }

    // -------------------------- Part3：排队队列方法（长隆主题文案） --------------------------
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingQueue.offer(visitor);
            System.out.println(" 长隆访客[" + visitor.getFullName() + "]（ID：" + visitor.getId() + "）已加入【" + rideName + "】排队队列");
        } else {
            System.out.println(" 无法添加空访客到【" + rideName + "】队列");
        }
    }

    @Override
    public void removeVisitorFromQueue() {
        Visitor removedVisitor = waitingQueue.poll();
        if (removedVisitor != null) {
            System.out.println(" 长隆访客[" + removedVisitor.getFullName() + "]（ID：" + removedVisitor.getId() + "）已从【" + rideName + "】队列移除，准备上车～");
        } else {
            System.out.println(" 【" + rideName + "】当前无排队访客，可直接体验！");
        }
    }

    @Override
    public void printQueue() {
        System.out.println("\n 长隆欢乐世界 - 【" + rideName + "】排队队列（当前等待：" + waitingQueue.size() + "人 | 单次载客：" + maxRider + "人）：");
        if (waitingQueue.isEmpty()) {
            System.out.println("   → 恭喜！当前无排队，立即体验垂直过山车～");
            return;
        }

        Iterator<Visitor> iterator = waitingQueue.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.printf("   %d. 姓名：%s | 访客类型：%s | 入园日期：%s%n",
                    index, visitor.getFullName(), visitor.getVisitorType(), visitor.getVisitDate());
            index++;
        }
        System.out.println("   💡 提示：垂直过山车全程2分30秒，包含90度俯冲和360度翻转，身高1.4米以上可体验～");
    }

    // -------------------------- Part4A-Part5：暂留空实现 --------------------------
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

    // toString（长隆主题适配）
    @Override
    public String toString() {
        return "长隆游乐设施{" +
                "设施ID='" + rideId + '\'' +
                ", 设施名称='" + rideName + '\'' +
                ", 专项操作员=" + (operator != null ? operator.getFullName() : "未分配") +
                ", 单次最大载客=" + maxRider +
                ", 今日运行次数=" + numOfCycles +
                ", 当前排队人数=" + waitingQueue.size() +
                ", 历史游玩人数=" + rideHistory.size() +
                '}';
    }
}