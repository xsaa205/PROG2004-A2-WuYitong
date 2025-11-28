package util;

import model.Visitor;

/**
 * Part2要求：定义游乐设施必须实现的核心行为，由Ride类实现
 * 包含排队、历史、运行相关的8个方法（对应Part3-Part5需求）
 */
public interface RideInterface {
    // -------------------------- 排队管理相关方法（Part3） --------------------------
    // 添加访客到排队队列
    void addVisitorToQueue(Visitor visitor);
    // 从排队队列移除队首访客
    void removeVisitorFromQueue();
    // 打印排队队列所有访客详情
    void printQueue();

    // -------------------------- 游玩历史相关方法（Part4A） --------------------------
    // 添加访客到游玩历史
    void addVisitorToHistory(Visitor visitor);
    // 校验访客是否在游玩历史中
    boolean checkVisitorFromHistory(Visitor visitor);
    // 返回游玩历史的访客总数
    int numberOfVisitors();
    // 打印游玩历史所有访客详情（需用Iterator）
    void printRideHistory();

    // -------------------------- 游乐设施运行方法（Part5） --------------------------
    // 运行一次游乐设施周期
    void runOneCycle();
}
