package model;

import util.RideInterface;
import util.VisitorComparator;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Part4A要求：实现游玩历史管理（添加、校验、统计、打印），适配长隆欢乐世界场景
 * Part4B要求：添加历史排序方法，基于VisitorComparator实现
 */
@SuppressWarnings("unused") // 抑制“未使用方法”的警告（Setter后续可能用）
public class Ride implements RideInterface {
    // Part2-Part3原有属性（加final消除“可设为final”的警告）
    private final String rideId;          // 长隆设施ID（初始化后不修改）
    private final String rideName;        // 长隆设施名称（初始化后不修改）
    private Employee operator;            // 操作员可能更换，不能加final
    private final Queue<Visitor> waitingQueue;    // 队列初始化后不重新赋值
    private final LinkedList<Visitor> rideHistory; // 历史集合初始化后不重新赋值
    private final int maxRider;           // 载客量初始化后不修改（或保留Setter，根据需求）
    private int numOfCycles;              // 运行次数会修改，不能加final

    // 带参构造器（初始化所有属性，长隆场景适配）
    public Ride(String rideId, String rideName, Employee operator, int maxRider) {
        this.rideId = rideId;
        this.rideName = rideName;
        this.operator = operator;
        this.maxRider = (maxRider >= 1) ? maxRider : 12; // 旋转木马默认12人

        // 初始化集合（避免空指针）
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // Getter与Setter（保留必要方法，消除“未使用”警告）
    public String getRideId() { return rideId; }
    // 移除rideId的Setter（因为rideId设为final，初始化后不修改）
    public String getRideName() { return rideName; }
    // 移除rideName的Setter（名称初始化后不修改）
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; } // 操作员可更换，保留Setter
    public int getMaxRider() { return maxRider; }
    // 若载客量无需修改，移除setMaxRider；若需要修改，保留并删除maxRider的final
    public int getNumOfCycles() { return numOfCycles; }

    // -------------------------- Part4A：游玩历史核心方法 --------------------------
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor);
            System.out.println(" 长隆访客[" + visitor.getFullName() + "（ID：" + visitor.getId() + "）]已添加到【" + rideName + "】游玩历史");
        } else {
            System.out.println(" 无法添加空访客到【" + rideName + "】游玩历史");
        }
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println(" 无法校验空访客");
            return false;
        }
        boolean isExists = rideHistory.contains(visitor);
        System.out.println(" 长隆访客[" + visitor.getFullName() + "（ID：" + visitor.getId() + "）]是否体验过【" + rideName + "】：" + isExists);
        return isExists;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println(" 长隆欢乐世界 - 【" + rideName + "】今日游玩历史总人数：" + count + "人");
        return count;
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n 长隆欢乐世界 - 【" + rideName + "】游玩历史（共" + rideHistory.size() + "人）：");
        if (rideHistory.isEmpty()) {
            System.out.println("   → 今日暂无访客体验" + rideName + "，可引导游客参与～");
            return;
        }
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.printf("   %d. 姓名：%s | ID：%s | 类型：%s | 入园日期：%s | 年龄：%d%n",
                    index, visitor.getFullName(), visitor.getId(),
                    visitor.getVisitorType(), visitor.getVisitDate(), visitor.getAge());
            index++;
        }
        if (rideName.contains("旋转木马")) {
            System.out.println("    长隆提示：梦幻旋转木马适合全年龄段，可提供亲子陪同服务～");
        } else if (rideName.contains("十环过山车")) {
            System.out.println("    长隆提示：十环过山车包含360度翻转，建议1.5米以上访客体验～");
        }
    }

    // -------------------------- Part4B：游玩历史排序方法 --------------------------
    public void sortRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println(" 【" + rideName + "】游玩历史为空，无需排序");
            return;
        }
        Collections.sort(rideHistory, new VisitorComparator());
        System.out.println(" 长隆欢乐世界 - 【" + rideName + "】游玩历史已排序（规则：入园日期升序 → 访客类型降序）");
    }

    // -------------------------- Part3方法（保持不变） --------------------------
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingQueue.offer(visitor);
            System.out.println(" 长隆访客[" + visitor.getFullName() + "（ID：" + visitor.getId() + "）]已加入【" + rideName + "】排队队列");
        } else {
            System.out.println(" 无法添加空访客到【" + rideName + "】队列");
        }
    }

    @Override
    public void removeVisitorFromQueue() {
        Visitor removedVisitor = waitingQueue.poll();
        if (removedVisitor != null) {
            System.out.println(" 长隆访客[" + removedVisitor.getFullName() + "（ID：" + removedVisitor.getId() + "）]已从【" + rideName + "】队列移除，准备上车～");
        } else {
            System.out.println(" 【" + rideName + "】当前无排队访客，可直接体验！");
        }
    }

    @Override
    public void printQueue() {
        System.out.println("\n 长隆欢乐世界 - 【" + rideName + "】排队队列（当前等待：" + waitingQueue.size() + "人 | 单次载客：" + maxRider + "人）：");
        if (waitingQueue.isEmpty()) {
            System.out.println("   → 恭喜！当前无排队，立即体验" + rideName + "～");
            return;
        }
        Iterator<Visitor> iterator = waitingQueue.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.printf("   %d. 姓名：%s | 类型：%s | 入园日期：%s%n",
                    index, visitor.getFullName(), visitor.getVisitorType(), visitor.getVisitDate());
            index++;
        }
        if (rideName.contains("垂直过山车")) {
            System.out.println("    长隆提示：垂直过山车全程2分30秒，含90度俯冲，身高1.4米以上可体验～");
        }
    }

    // -------------------------- Part5方法（暂留空） --------------------------
    @Override
    public void runOneCycle() {}

    // 重写toString
    @Override
    public String toString() {
        return "长隆游乐设施{" +
                "设施ID='" + rideId + '\'' +
                ", 设施名称='" + rideName + '\'' +
                ", 专项操作员=" + (operator != null ? operator.getFullName() : "未分配") +
                ", 单次最大载客=" + maxRider +
                ", 今日运行次数=" + numOfCycles +
                ", 当前排队人数=" + waitingQueue.size() +
                ", 今日游玩人数=" + rideHistory.size() +
                '}';
    }
}