import model.Employee;
import model.Ride;
import model.Visitor;
import java.time.LocalDate;

/**
 * Part5：长隆欢乐世界超级大摆锤运行周期演示
 */
@SuppressWarnings({"all"}) // 抑制所有类型的警告（包括未使用方法、拼写等）
public class AssignmentTwo {

    // 程序入口：仅演示Part5
    public static void main(String[] args) {
        System.out.println("==================================== 长隆欢乐世界 - 游乐设施管理系统 ====================================");
        new AssignmentTwo().partFive(); // 直接调用partFive，避免demo变量冗余
        System.out.println("\n==================================== 功能演示完成 ====================================");
    }

    // -------------------------- Part5：运行Ride周期演示（超级大摆锤） --------------------------
    public void partFive() {
        System.out.println("\n==================================== Part5：运行Ride周期演示（超级大摆锤） ====================================");
        // 1. 创建长隆专项操作员
        Employee swingOperator = new Employee(
                "CL-EMP-011",
                "王师傅",
                29,
                "超级大摆锤专项操作员",
                "CL-RIDE-004"
        );
        System.out.println(" 初始化操作员：" + swingOperator.introduceYourself());

        // 2. 创建超级大摆锤
        Ride superSwing = new Ride(
                "CL-RIDE-004",
                "超级大摆锤",
                swingOperator,
                4
        );
        System.out.println(" 初始化设施：" + superSwing);

        // 3. 添加10个长隆访客到队列
        System.out.println("\n 长隆访客入园，加入超级大摆锤队列：");
        for (int i = 1; i <= 10; i++) {
            String visitorId = "CL-VIS-0" + (20 + i);
            String visitorName = "访客" + (20 + i);
            int age = 18 + (i % 20);
            String visitorType = (i % 3 == 0) ? "VIP访客" : (i % 4 == 0) ? "快速通道访客" : "普通访客";
            LocalDate visitDate = LocalDate.of(2025, 12, 4);

            superSwing.addVisitorToQueue(new Visitor(visitorId, visitorName, age, visitorType, visitDate));
        }

        // 4. 打印运行前队列
        System.out.println("\n 运行前的排队队列：");
        superSwing.printQueue();

        // 5. 运行周期
        System.out.println("\n 启动超级大摆锤运行周期：");
        superSwing.runOneCycle();

        // 6. 打印运行后队列
        System.out.println("\n 运行后的排队队列：");
        superSwing.printQueue();

        // 7. 打印运行后历史
        System.out.println("\n 运行后的游玩历史：");
        superSwing.printRideHistory();
    }

    // 其他Part方法（后续实现，添加Javadoc说明避免警告）
    /** Part3：排队队列演示（后续实现） */
    public void partThree() {}
    /** Part4A：游玩历史演示（后续实现） */
    public void partFourA() {}
    /** Part4B：历史排序演示（后续实现） */
    public void partFourB() {}
    /** Part6：导出历史到文件（后续实现） */
    public void partSix() {}
    /** Part7：从文件导入历史（后续实现） */
    public void partSeven() {}
}