package model;

// 只保留实际用到的import
import util.RideInterface;
import util.VisitorComparator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

// （删除未使用的import，比如：import java.util.Collections;）

@SuppressWarnings("unused")
public class Ride implements RideInterface {
    // 属性定义（修正拼写，确保与接口一致）
    private final String rideId;          // 长隆设施ID
    private final String rideName;        // 长隆设施名称
    private Employee operator;            // 长隆专项操作员
    private final Queue<Visitor> waitingQueue;    // 排队队列
    private final LinkedList<Visitor> rideHistory; // 游玩历史（修正拼写：rideHistory）
    private final int maxRider;           // 单次最大载客量
    private int numOfCycles;              // 今日运行次数

    // 构造器（确保属性初始化正确）
    public Ride(String rideId, String rideName, Employee operator, int maxRider) {
        this.rideId = rideId;
        this.rideName = rideName;
        this.operator = operator;
        this.maxRider = (maxRider >= 1) ? maxRider : 6;

        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>(); // 修正拼写：rideHistory
        this.numOfCycles = 0;
    }

    // Getter与Setter（确保方法名正确）
    public String getRideId() { return rideId; }
    public String getRideName() { return rideName; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public int getNumOfCycles() { return numOfCycles; }

    // -------------------------- Part6：导出历史（正确实现接口方法） --------------------------
    @Override
    public void exportRideHistory(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("❌ 文件路径不能为空");
            return;
        }
        if (rideHistory.isEmpty()) { // 修正拼写：rideHistory
            System.out.println("❌ 【" + rideName + "】游玩历史为空，无需导出");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("visitor_id,full_name,age,visitor_type,visit_date");
            writer.newLine();
            for (Visitor visitor : rideHistory) { // 修正拼写：rideHistory
                String line = String.join(",",
                        visitor.getId(),
                        visitor.getFullName(),
                        String.valueOf(visitor.getAge()),
                        visitor.getVisitorType(),
                        visitor.getVisitDate().toString()
                );
                writer.write(line);
                writer.newLine();
            }
            System.out.println("✅ 【" + rideName + "】游玩历史已导出到：" + filePath);
        } catch (IOException e) {
            System.out.println("❌ 导出失败：" + e.getMessage());
        }
    }

    // -------------------------- Part5：运行周期（正确实现接口方法） --------------------------
    @Override
    public void runOneCycle() {
        if (operator == null) {
            System.out.println("❌ 【" + rideName + "】未分配操作员");
            return;
        }
        if (waitingQueue.isEmpty()) {
            System.out.println("❌ 【" + rideName + "】队列为空");
            return;
        }
        System.out.println("\n🚀 【" + rideName + "】开始运行第" + (numOfCycles + 1) + "周期");
        System.out.println("🔧 操作员" + operator.getFullName() + "正在安全检查...");
        int actual = 0;
        while (actual < maxRider && !waitingQueue.isEmpty()) {
            Visitor rider = waitingQueue.poll();
            addVisitorToHistory(rider);
            actual++;
        }
        numOfCycles++;
        System.out.println("✅ 【" + rideName + "】第" + numOfCycles + "周期完成，载客" + actual + "人");
    }

    // -------------------------- Part4A：游玩历史方法（正确实现接口） --------------------------
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor); // 修正拼写：rideHistory
        }
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) return false;
        return rideHistory.contains(visitor); // 修正拼写：rideHistory
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size(); // 修正拼写：rideHistory
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n📜 【" + rideName + "】游玩历史（共" + rideHistory.size() + "人）："); // 修正拼写
        if (rideHistory.isEmpty()) { // 修正拼写
            System.out.println("   → 暂无访客体验");
            return;
        }
        Iterator<Visitor> it = rideHistory.iterator(); // 修正拼写
        int idx = 1;
        while (it.hasNext()) {
            Visitor v = it.next();
            System.out.printf("   %d. 姓名：%s | ID：%s | 类型：%s | 日期：%s%n",
                    idx, v.getFullName(), v.getId(), v.getVisitorType(), v.getVisitDate());
            idx++;
        }
        if (rideName.contains("摩天轮")) {
            System.out.println("   💡 长隆提示：巨型摩天轮可俯瞰园区全景");
        }
    }

    // Part4B：排序方法（将Collections.sort替换为List.sort）
    public void sortRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("❌ 历史为空，无需排序");
            return;
        }
        // 替换：Collections.sort(rideHistory, new VisitorComparator())
        rideHistory.sort(new VisitorComparator());
        System.out.println("✅ 【" + rideName + "】历史已排序");
    }

    // -------------------------- Part3：排队方法（正确实现接口） --------------------------
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingQueue.offer(visitor);
            System.out.println("✅ 访客[" + visitor.getFullName() + "]已加入【" + rideName + "】队列");
        }
    }

    @Override
    public void removeVisitorFromQueue() {
        Visitor removed = waitingQueue.poll();
        if (removed != null) {
            System.out.println("✅ 访客[" + removed.getFullName() + "]已离队");
        }
    }

    @Override
    public void printQueue() {
        System.out.println("\n📋 【" + rideName + "】队列（等待：" + waitingQueue.size() + "人）：");
        if (waitingQueue.isEmpty()) {
            System.out.println("   → 当前无排队");
            return;
        }
        Iterator<Visitor> it = waitingQueue.iterator();
        int idx = 1;
        while (it.hasNext()) {
            Visitor v = it.next();
            System.out.printf("   %d. 姓名：%s | 类型：%s%n", idx, v.getFullName(), v.getVisitorType());
            idx++;
        }
    }

    // toString方法
    @Override
    public String toString() {
        return "长隆设施{" +
                "ID='" + rideId + '\'' +
                ", 名称='" + rideName + '\'' +
                ", 操作员=" + (operator != null ? operator.getFullName() : "未分配") +
                ", 载客量=" + maxRider +
                '}';
    }
}