import model.Employee;
import model.Ride;
import model.Visitor;
import java.time.LocalDate;

/**
 * Part3要求：长隆欢乐世界 - 垂直过山车排队功能演示
 */
public class AssignmentTwo {

    // 程序入口（长隆主题标题）
    public static void main(String[] args) {
        System.out.println("==================================== 长隆欢乐世界 - 游乐设施管理系统 ====================================");
        System.out.println(" 欢迎体验长隆标志性项目：垂直过山车（亚洲第一座无地板过山车）");
        AssignmentTwo demo = new AssignmentTwo();

        demo.partThree(); // 启用排队功能演示

        // 其他Part方法暂注释
        // demo.partFourA();
        // demo.partFourB();
        // demo.partFive();
        // demo.partSix();
        // demo.partSeven();

        System.out.println("\n==================================== 长隆欢乐世界 - 功能演示完成 ====================================");
    }

    // -------------------------- Part3：长隆垂直过山车排队演示 --------------------------
    public void partThree() {
        System.out.println("\n==================================== Part3：垂直过山车排队功能演示 ====================================");

        // 1. 创建长隆专项操作员（垂直过山车持证操作员）
        Employee verticalCoasterOperator = new Employee(
                "CL-EMP-008",  // 长隆员工ID编码
                "陈师傅",       // 操作员姓名（符合景区常见称呼）
                38,            // 操作员年龄（经验丰富）
                "垂直过山车专项操作员", // 岗位名称
                "CL-RIDE-001"  // 负责设施ID（长隆编码）
        );
        System.out.println(" 初始化操作员：" + verticalCoasterOperator.introduceYourself());

        // 2. 创建长隆垂直过山车（长隆标志性设施）
        Ride verticalCoaster = new Ride(
                "CL-RIDE-001",
                "垂直过山车",
                verticalCoasterOperator,
                8 // 单次载客8人（简化设定）
        );
        System.out.println(" 初始化设施：" + verticalCoaster);

        // 3. 添加6个长隆访客（含长隆常见访客类型）
        System.out.println("\n 长隆访客入园，加入垂直过山车队列：");
        verticalCoaster.addVisitorToQueue(new Visitor("CL-VIS-001", "李明", 28, "普通访客", LocalDate.of(2025, 12, 1)));
        verticalCoaster.addVisitorToQueue(new Visitor("CL-VIS-002", "王芳", 32, "家庭访客", LocalDate.of(2025, 12, 1)));
        verticalCoaster.addVisitorToQueue(new Visitor("CL-VIS-003", "张伟", 25, "快速通道访客", LocalDate.of(2025, 12, 1)));
        verticalCoaster.addVisitorToQueue(new Visitor("CL-VIS-004", "刘敏", 22, "普通访客", LocalDate.of(2025, 12, 1)));
        verticalCoaster.addVisitorToQueue(new Visitor("CL-VIS-005", "陈浩", 30, "家庭访客", LocalDate.of(2025, 12, 1)));
        verticalCoaster.addVisitorToQueue(new Visitor("CL-VIS-006", "林小雅", 26, "快速通道访客", LocalDate.of(2025, 12, 1)));

        // 4. 第一次打印队列（查看排队情况）
        verticalCoaster.printQueue();

        // 5. 移除3个访客（模拟上车体验）
        System.out.println("\n 垂直过山车即将发车，移除前3名访客上车：");
        verticalCoaster.removeVisitorFromQueue();
        verticalCoaster.removeVisitorFromQueue();
        verticalCoaster.removeVisitorFromQueue();

        // 6. 第二次打印队列（剩余排队情况）
        verticalCoaster.printQueue();

        // 7. 测试空队列场景（模拟所有访客上车）
        System.out.println("\n  测试队列清空场景：");
        verticalCoaster.removeVisitorFromQueue();
        verticalCoaster.removeVisitorFromQueue();
        verticalCoaster.removeVisitorFromQueue(); // 队列已空
        verticalCoaster.removeVisitorFromQueue(); // 测试空队列移除
    }

    // 其他Part方法占位符（不变）
    public void partFourA() {}
    public void partFourB() {}
    public void partFive() {}
    public void partSix() {}
    public void partSeven() {}
}