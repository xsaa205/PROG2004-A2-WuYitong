import model.Employee;
import model.Ride;
import model.Visitor;
import java.time.LocalDate;

/**
 * Part4A+Part4B演示：长隆欢乐世界游玩历史管理与排序
 * 包含梦幻旋转木马、十环过山车的完整场景演示
 */
public class AssignmentTwo {

    // 程序入口（启用Part4A+Part4B演示）
    public static void main(String[] args) {
        System.out.println("==================================== 长隆欢乐世界 - 游乐设施管理系统 ====================================");
        System.out.println(" 今日演示：Part4A游玩历史（梦幻旋转木马） | Part4B历史排序（十环过山车）");
        AssignmentTwo demo = new AssignmentTwo();

        demo.partFourA(); // Part4A：游玩历史演示
        demo.partFourB(); // Part4B：历史排序演示

        System.out.println("\n==================================== 长隆欢乐世界 - 功能演示完成 ====================================");
    }

    // -------------------------- Part4A：游玩历史演示（长隆梦幻旋转木马，满足作业"至少5个访客"要求） --------------------------
    public void partFourA() {
        System.out.println("\n==================================== Part4A：游玩历史演示（梦幻旋转木马） ====================================");
        // 1. 创建长隆专项操作员（旋转木马需儿童友好型操作员）
        Employee carouselOperator = new Employee(
                "CL-EMP-009",  // 长隆员工ID（CL=Chimelong，EMP=员工）
                "李姐",        // 景区常见称呼，亲和力强
                28,           // 适合儿童服务的年龄
                "旋转木马专项操作员", // 长隆岗位名称
                "CL-RIDE-002" // 对应设施ID
        );
        System.out.println(" 初始化操作员：" + carouselOperator.introduceYourself());

        // 2. 创建长隆梦幻旋转木马（单次载客12人，长隆真实值简化）
        Ride carousel = new Ride(
                "CL-RIDE-002",
                "梦幻旋转木马",
                carouselOperator,
                12
        );
        System.out.println(" 初始化设施：" + carousel);

        // 3. 添加6个长隆访客到游玩历史（满足"至少5个"要求，覆盖所有访客类型）
        System.out.println("\n 长隆访客体验旋转木马，添加到游玩历史：");
        Visitor v1 = new Visitor("CL-VIS-007", "张小宝", 6, "儿童访客", LocalDate.of(2025, 12, 2));
        Visitor v2 = new Visitor("CL-VIS-008", "李小丫", 5, "儿童访客", LocalDate.of(2025, 12, 2));
        Visitor v3 = new Visitor("CL-VIS-009", "王爸爸", 35, "家庭访客", LocalDate.of(2025, 12, 2));
        Visitor v4 = new Visitor("CL-VIS-010", "赵妈妈", 33, "家庭访客", LocalDate.of(2025, 12, 2));
        Visitor v5 = new Visitor("CL-VIS-011", "陈乐乐", 7, "儿童访客", LocalDate.of(2025, 12, 2));
        Visitor v6 = new Visitor("CL-VIS-012", "刘VIP", 30, "VIP访客", LocalDate.of(2025, 12, 1)); // 早入园的VIP

        carousel.addVisitorToHistory(v1);
        carousel.addVisitorToHistory(v2);
        carousel.addVisitorToHistory(v3);
        carousel.addVisitorToHistory(v4);
        carousel.addVisitorToHistory(v5);
        carousel.addVisitorToHistory(v6);

        // 4. 校验访客是否在历史中（覆盖"存在"和"不存在"两种场景）
        System.out.println("\n 校验访客是否在游玩历史中：");
        carousel.checkVisitorFromHistory(v3); // 存在：王爸爸（家庭访客）
        carousel.checkVisitorFromHistory(new Visitor("CL-VIS-999", "不存在的访客", 20, "普通访客", LocalDate.now())); // 不存在

        // 5. 统计并打印游玩总人数（作业要求：打印总数）
        System.out.println("\n 统计游玩历史总人数：");
        carousel.numberOfVisitors();

        // 6. 打印完整游玩历史（作业要求：用Iterator，长隆风格格式）
        System.out.println("\n 打印完整游玩历史：");
        carousel.printRideHistory();
    }

    // -------------------------- Part4B：历史排序演示（长隆十环过山车，满足"添加+排序+打印前后"要求） --------------------------
    public void partFourB() {
        System.out.println("\n==================================== Part4B：历史排序演示（十环过山车） ====================================");
        // 1. 创建长隆专项操作员（十环过山车需专业持证人员）
        Employee tenLoopOperator = new Employee(
                "CL-EMP-010",
                "张师傅",
                35,
                "十环过山车专项操作员",
                "CL-RIDE-003"
        );
        System.out.println(" 初始化操作员：" + tenLoopOperator.introduceYourself());

        // 2. 创建长隆十环过山车（单次载客6人，长隆真实值简化）
        Ride tenLoopCoaster = new Ride(
                "CL-RIDE-003",
                "十环过山车",
                tenLoopOperator,
                6
        );
        System.out.println(" 初始化设施：" + tenLoopCoaster);

        // 3. 添加6个长隆访客到游玩历史（日期+类型打乱，便于排序演示）
        System.out.println("\n 长隆访客体验十环过山车，添加到游玩历史（未排序）：");
        tenLoopCoaster.addVisitorToHistory(new Visitor("CL-VIS-013", "刘阳", 22, "普通访客", LocalDate.of(2025, 12, 3)));
        tenLoopCoaster.addVisitorToHistory(new Visitor("CL-VIS-014", "黄丽", 28, "VIP访客", LocalDate.of(2025, 12, 2)));
        tenLoopCoaster.addVisitorToHistory(new Visitor("CL-VIS-015", "周明", 33, "家庭访客", LocalDate.of(2025, 12, 2)));
        tenLoopCoaster.addVisitorToHistory(new Visitor("CL-VIS-016", "吴婷", 25, "快速通道访客", LocalDate.of(2025, 12, 3)));
        tenLoopCoaster.addVisitorToHistory(new Visitor("CL-VIS-017", "郑涛", 30, "普通访客", LocalDate.of(2025, 12, 1)));
        tenLoopCoaster.addVisitorToHistory(new Visitor("CL-VIS-018", "马快速", 27, "快速通道访客", LocalDate.of(2025, 12, 2)));

        // 4. 打印排序前的历史（验证初始顺序）
        System.out.println("\n 排序前的游玩历史（初始添加顺序）：");
        tenLoopCoaster.printRideHistory();

        // 5. 执行排序（调用Ride的sortRideHistory，使用VisitorComparator）
        System.out.println("\n 执行游玩历史排序（规则：入园日期升序 → 访客类型降序）：");
        tenLoopCoaster.sortRideHistory();

        // 6. 打印排序后的历史（验证排序结果，符合长隆运营优先级）
        System.out.println("\n 排序后的游玩历史（长隆运营优先级）：");
        tenLoopCoaster.printRideHistory();
        System.out.println("    排序结果说明：12月1日访客在前 → 同日期下快速通道>VIP>家庭>普通");
    }

    // -------------------------- 其他Part方法（保持占位，后续实现） --------------------------
    public void partThree() {}
    public void partFive() {}
    public void partSix() {}
    public void partSeven() {}
}