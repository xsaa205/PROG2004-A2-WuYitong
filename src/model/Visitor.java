package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Part1要求：继承Person类，封装主题公园访客的专属属性与行为
 * 包含2个专属实例变量、默认/带参构造器、getter/setter
 */
public class Visitor extends Person {
    // 2个专属实例变量（满足Part1"至少2个"要求，支持后续排序与历史管理）
    private String visitorType;    // 访客类型（如"普通访客"/"VIP访客"）
    private LocalDate visitDate;   // 来访日期（用于Part4B排序）

    // 1. 默认构造器（满足Part1要求）
    public Visitor() {}

    // 2. 带参构造器：初始化父类+子类属性
    public Visitor(String id, String fullName, int age, String visitorType, LocalDate visitDate) {
        super(id, fullName, age);
        this.visitorType = visitorType;
        this.visitDate = visitDate;
    }

    // 3. Getter与Setter（封装专属属性，满足Part1要求）
    public String getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    // 4. 实现父类抽象方法：访客自我介绍（体现多态）
    @Override
    public String introduceYourself() {
        return "【访客】" + getFullName() + "，类型：" + visitorType + "，来访日期：" + visitDate;
    }

    // 5. 重写toString：便于打印队列与历史详情
    @Override
    public String toString() {
        return "Visitor{" +
                "visitorType='" + visitorType + '\'' +
                ", visitDate=" + visitDate +
                "} " + super.toString();
    }

    // 6. 重写equals与hashCode：按ID判断唯一性（Part4A历史校验核心）
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitor visitor = (Visitor) o;
        return Objects.equals(getId(), visitor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}