package util;

import model.Visitor;

// 确保接口的方法签名与Ride类的实现完全一致
public interface RideInterface {
    // Part3：排队相关
    void addVisitorToQueue(Visitor visitor);
    void removeVisitorFromQueue();
    void printQueue();

    // Part4A：游玩历史相关
    void addVisitorToHistory(Visitor visitor);
    boolean checkVisitorFromHistory(Visitor visitor);
    int numberOfVisitors();
    void printRideHistory();

    // Part5：运行周期
    void runOneCycle();

    // Part6：导出历史
    void exportRideHistory(String filePath);
}
