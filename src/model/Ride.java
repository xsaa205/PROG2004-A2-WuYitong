package model;

import util.RideInterface;
import util.VisitorComparator;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unused") // 抑制未使用方法的警告
public class Ride implements RideInterface {
    // Part2-Part4原有属性（长隆场景适配）
    private final String rideId;          // 长隆设施ID
    private final String rideName;        // 长隆设施名称
    private Employee operator;            // 长隆专项操作员
    private final Queue<Visitor> waitingQueue;    // 排队队列
    private final LinkedList<Visitor> rideHistory; // 游玩历史
    private final int maxRider;           // 单次最大载客量
    private int numOfCycles;              // 今日运行次数

    // 带参构造器（初始化所有属性）
    public Ride(String rideId, String rideName, Employee operator, int maxRider) {
        this.rideId = rideId;
        this.rideName = rideName;
        this.operator = operator;
        this.maxRider = (maxRider >= 1) ? maxRider : 4; // 大摆锤默认4人

        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // Getter与Setter
    public String getRideId() { return rideId; }
    public String getRideName() { return rideName; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public int getNumOfCycles() { return numOfCycles; }

    // -------------------------- Part5：运行Ride周期（核心方法） --------------------------
    @Override
    public void runOneCycle() {
        // 前置校验1：是否分配长隆专项操作员
        if (operator == null) {
            System.out.println(" 【" + rideName + "】未分配长隆专项操作员，无法运行");
            return;
        }

        // 前置校验2：排队队列是否有访客
        if (waitingQueue.isEmpty()) {
            System.out.println(" 【" + rideName + "】排队队列为空，无法运行");
            return;
        }

        // 核心逻辑：长隆设施运行流程（安全检查→载客→更新历史）
        System.out.println("\n 长隆欢乐世界 - 【" + rideName + "】开始运行第" + (numOfCycles + 1) + "周期（单次最大载客：" + maxRider + "人）");
        System.out.println("🔧 操作员" + operator.getFullName() + "正在进行安全检查（安全带+设备稳定性）...");
        int actualRiderCount = 0;  // 实际载客数（队列不足时小于maxRider）

        // 从队列取访客，添加到游玩历史
        while (actualRiderCount < maxRider && !waitingQueue.isEmpty()) {
            Visitor rider = waitingQueue.poll();
            addVisitorToHistory(rider);
            System.out.println(" 已载客：" + rider.getFullName() + "（ID：" + rider.getId() + "）");
            actualRiderCount++;
        }

        // 更新运行次数+完成提示
        numOfCycles++;
        System.out.println(" 【" + rideName + "】第" + numOfCycles + "周期运行完成！本次载客：" + actualRiderCount + "人，祝您游玩愉快～");
    }

    // -------------------------- Part4A：游玩历史方法（已实现） --------------------------
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor);
            // （内部调用，无需重复打印提示）
        }
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) return false;
        return rideHistory.contains(visitor);
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size();
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n 长隆欢乐世界 - 【" + rideName + "】游玩历史（共" + rideHistory.size() + "人）：");
        if (rideHistory.isEmpty()) {
            System.out.println("   → 今日暂无访客体验" + rideName);
            return;
        }

        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.printf("   %d. 姓名：%s | ID：%s | 类型：%s | 入园日期：%s%n",
                    index, visitor.getFullName(), visitor.getId(),
                    visitor.getVisitorType(), visitor.getVisitDate());
            index++;
        }
        if (rideName.contains("超级大摆锤")) {
            System.out.println("    长隆提示：超级大摆锤摆幅达120度，建议1.4米以上访客体验～");
        }
    }

    // -------------------------- Part4B：排序方法（已实现） --------------------------
    public void sortRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println(" 【" + rideName + "】游玩历史为空，无需排序");
            return;
        }
        Collections.sort(rideHistory, new VisitorComparator());
        System.out.println(" 【" + rideName + "】游玩历史已排序");
    }

    // -------------------------- Part3：排队方法（已实现） --------------------------
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingQueue.offer(visitor);
            System.out.println(" 长隆访客[" + visitor.getFullName() + "（ID：" + visitor.getId() + "）]已加入【" + rideName + "】队列");
        }
    }

    @Override
    public void removeVisitorFromQueue() {
        Visitor removed = waitingQueue.poll();
        if (removed != null) {
            System.out.println(" 长隆访客[" + removed.getFullName() + "]已从【" + rideName + "】队列移除");
        }
    }

    @Override
    public void printQueue() {
        System.out.println("\n 长隆欢乐世界 - 【" + rideName + "】排队队列（当前等待：" + waitingQueue.size() + "人 | 单次载客：" + maxRider + "人）：");
        if (waitingQueue.isEmpty()) {
            System.out.println("   → 当前无排队，立即体验～");
            return;
        }

        Iterator<Visitor> iterator = waitingQueue.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.printf("   %d. 姓名：%s | 类型：%s%n",
                    index, visitor.getFullName(), visitor.getVisitorType());
            index++;
        }
    }

    // 重写toString
    @Override
    public String toString() {
        return "长隆游乐设施{" +
                "设施ID='" + rideId + '\'' +
                ", 名称='" + rideName + '\'' +
                ", 操作员=" + (operator != null ? operator.getFullName() : "未分配") +
                ", 单次载客=" + maxRider +
                ", 今日运行次数=" + numOfCycles +
                ", 排队人数=" + waitingQueue.size() +
                ", 游玩人数=" + rideHistory.size() +
                '}';
    }
}