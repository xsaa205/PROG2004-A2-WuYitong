import model.Employee;
import model.Ride;
import model.Visitor;
import java.time.LocalDate;

@SuppressWarnings({"all"}) // 抑制所有警告
public class AssignmentTwo {

    // 程序入口：仅演示Part6
    public static void main(String[] args) {
        System.out.println("==================================== 长隆欢乐世界 - 数据导出演示 ====================================");
        new AssignmentTwo().partSix();
        System.out.println("\n==================================== 演示完成 ====================================");
    }

    // -------------------------- Part6：导出历史到文件演示（巨型摩天轮） --------------------------
    public void partSix() {
        System.out.println("\n==================================== Part6：导出历史到文件（巨型摩天轮） ====================================");
        // 1. 创建长隆摩天轮操作员
        Employee ferrisWheelOperator = new Employee(
                "CL-EMP-012",
                "秦师傅",
                32,
                "摩天轮专项操作员",
                "CL-RIDE-005"
        );
        System.out.println("👨‍🔧 初始化操作员：" + ferrisWheelOperator.introduceYourself());

        // 2. 创建长隆巨型摩天轮（单次载客6人）
        Ride ferrisWheel = new Ride(
                "CL-RIDE-005",
                "巨型摩天轮",
                ferrisWheelOperator,
                6
        );
        System.out.println("🎡 初始化设施：" + ferrisWheel);

        // 3. 添加5个长隆访客到游玩历史（满足"至少5个"要求）
        System.out.println("\n📥 添加访客到摩天轮游玩历史：");
        ferrisWheel.addVisitorToHistory(new Visitor("CL-VIS-031", "尤佳", 24, "普通访客", LocalDate.of(2025, 12, 5)));
        ferrisWheel.addVisitorToHistory(new Visitor("CL-VIS-032", "许晴", 26, "VIP访客", LocalDate.of(2025, 12, 5)));
        ferrisWheel.addVisitorToHistory(new Visitor("CL-VIS-033", "何伟", 29, "家庭访客", LocalDate.of(2025, 12, 5)));
        ferrisWheel.addVisitorToHistory(new Visitor("CL-VIS-034", "吕洋", 31, "快速通道访客", LocalDate.of(2025, 12, 5)));
        ferrisWheel.addVisitorToHistory(new Visitor("CL-VIS-035", "施然", 27, "普通访客", LocalDate.of(2025, 12, 5)));

        // 4. 导出历史到CSV文件（长隆命名规则：cl_设施名_history.csv）
        String exportPath = "cl_ferris_wheel_history.csv";
        System.out.println("\n📤 导出游玩历史到文件：");
        ferrisWheel.exportRideHistory(exportPath);

        // 5. 打印待导出的历史（验证数据）
        System.out.println("\n🔸 待导出的游玩历史详情：");
        ferrisWheel.printRideHistory();
    }

    // 其他Part方法（占位，添加注释避免警告）
    /** Part3：排队队列演示 */
    public void partThree() {}
    /** Part4A：游玩历史演示 */
    public void partFourA() {}
    /** Part4B：历史排序演示 */
    public void partFourB() {}
    /** Part5：运行周期演示 */
    public void partFive() {}
    /** Part7：导入历史演示 */
    public void partSeven() {}
}