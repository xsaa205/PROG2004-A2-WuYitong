package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Part2要求：实现Person类的抽象方法introduceYourself()
 */
public class Visitor extends Person {
    // Part1原有属性（不变）
    private String visitorType;    // 访客类型（如"普通访客"/"VIP访客"）
    private LocalDate visitDate;   // 来访日期（用于Part4B排序）

    // Part1原有构造器（不变）
    public Visitor() {}

    public Visitor(String id, String fullName, int age, String visitorType, LocalDate visitDate) {
        super(id, fullName, age);
        this.visitorType = visitorType;
        this.visitDate = visitDate;
    }

    // Part1原有Getter/Setter（不变）
    public String getVisitorType() { return visitorType; }
    public void setVisitorType(String visitorType) { this.visitorType = visitorType; }
    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    // -------------------------- Part2新增：实现抽象方法 --------------------------
    @Override
    public String introduceYourself() {
        return "【访客】" + getFullName() + "（ID：" + getId() + "），类型：" + visitorType + "，来访日期：" + visitDate;
    }

    // Part1原有toString/equals/hashCode（不变）
    @Override
    public String toString() {
        return "Visitor{" +
                "visitorType='" + visitorType + '\'' +
                ", visitDate=" + visitDate +
                "} " + super.toString();
    }

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