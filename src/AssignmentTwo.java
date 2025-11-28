import model.Employee;
import model.Ride;
import model.Visitor;
import java.time.LocalDate;

/**
 * Part2测试：验证抽象类、接口、子类实现是否正常工作
 */
public class AssignmentTwo {

    // 程序入口
    public static void main(String[] args) {
        System.out.println("==================================== PROG2004 A2 主题公园管理系统（Part2测试） ====================================");
        AssignmentTwo demo = new AssignmentTwo();
        demo.testPart2Functions(); // 测试Part2新增功能

        System.out.println("\n==================================== Part2测试完成 ====================================");
    }

    // 测试Part2核心功能（抽象类、接口、子类实现）
    public void testPart2Functions() {
        // 1. 测试抽象类+子类多态（Person→Employee/Visitor）
        System.out.println("\n🔹 测试抽象类与多态：");
        Employee operator = new Employee(
                "EMP-001",
                "张三",
                30,
                "过山车操作员",
                "RC-001"
        );
        Visitor vipVisitor = new Visitor(
                "VIS-001",
                "李四",
                25,
                "VIP访客",
                LocalDate.of(2025, 11, 1)
        );
        // 调用抽象方法（多态体现：不同子类有不同实现）
        System.out.println(operator.introduceYourself());
        System.out.println(vipVisitor.introduceYourself());

        // 2. 测试Ride类（实现接口+新增属性）
        System.out.println("\n🔹 测试Ride类（实现接口+新增属性）：");
        Ride rollerCoaster = new Ride(
                "RC-001",
                "超级过山车",
                operator,
                2 // 单次最大载客2人
        );
        System.out.println(rollerCoaster); // 打印toString，验证新增属性
    }

    // Part3-Part7方法占位符（不变）
    public void partThree() {}
    public void partFourA() {}
    public void partFourB() {}
    public void partFive() {}
    public void partSix() {}
    public void partSeven() {}
}