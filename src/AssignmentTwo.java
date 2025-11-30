import model.Employee;
import model.Ride;
import model.Visitor;
import java.time.LocalDate;

@SuppressWarnings({"all"}) // Suppress unused variable/method warnings
public class AssignmentTwo {

    public static void main(String[] args) {
        System.out.println("==================================== Chimelong Paradise - Ride Management System (CLHWMS) ====================================");
        System.out.println(" Function Coverage: Queue Management → Ride History → History Sorting → Operation Cycles → Data Export → Data Import");
        System.out.println(" Core Attractions: Vertical Coaster, Dream Carousel, Ten-Loop Coaster, Super Swing, Giant Ferris Wheel");
        System.out.println("============================================================================================================\n");

        AssignmentTwo demo = new AssignmentTwo();

        // Execute all demonstrations in order (Part3-Part7)
        demo.partThree();   // Vertical Coaster - Queue Management
        demo.partFourA();   // Dream Carousel - Ride History Management
        demo.partFourB();   // Ten-Loop Coaster - History Sorting
        demo.partFive();    // Super Swing - Operation Cycles
        demo.partSix();     // Giant Ferris Wheel - History Export
        demo.partSeven();   // Giant Ferris Wheel - History Import

        System.out.println("\n==================================== Chimelong Paradise Management System - Full Demo Completed ====================================");
    }

    // Part3: Queue Management Demo (Vertical Coaster)
    public void partThree() {
        System.out.println("==================================== Part3: Queue Management (Vertical Coaster) ====================================");
        Employee verticalCoasterOperator = new Employee(
                "CL-EMP-008",
                "Mr. Chen",
                38,
                "Vertical Coaster Specialist",
                "CL-RIDE-001"
        );
        System.out.println(" " + verticalCoasterOperator.introduceYourself());

        Ride verticalCoaster = new Ride(
                "CL-RIDE-001",
                "Vertical Coaster",
                verticalCoasterOperator,
                8
        );
        System.out.println(" Ride Info: " + verticalCoaster);

        System.out.println("\n Visitors entering park, joining Vertical Coaster queue:");
        verticalCoaster.addVisitorToQueue(new Visitor("CL-VIS-001", "Li Ming", 28, "General Visitor", LocalDate.of(2025, 12, 1)));
        verticalCoaster.addVisitorToQueue(new Visitor("CL-VIS-002", "Wang Fang", 32, "Family Visitor", LocalDate.of(2025, 12, 1)));
        verticalCoaster.addVisitorToQueue(new Visitor("CL-VIS-003", "Zhang Wei", 25, "Fast Pass Visitor", LocalDate.of(2025, 12, 1)));
        verticalCoaster.addVisitorToQueue(new Visitor("CL-VIS-004", "Liu Min", 22, "General Visitor", LocalDate.of(2025, 12, 1)));
        verticalCoaster.addVisitorToQueue(new Visitor("CL-VIS-005", "Chen Hao", 30, "Family Visitor", LocalDate.of(2025, 12, 1)));
        verticalCoaster.addVisitorToQueue(new Visitor("CL-VIS-006", "Lin Xiaoya", 26, "Fast Pass Visitor", LocalDate.of(2025, 12, 1)));

        verticalCoaster.printQueue();

        System.out.println("\n Vertical Coaster departs, removing first 3 visitors:");
        verticalCoaster.removeVisitorFromQueue();
        verticalCoaster.removeVisitorFromQueue();
        verticalCoaster.removeVisitorFromQueue();

        verticalCoaster.printQueue();

        System.out.println("\n Testing queue clear:");
        verticalCoaster.removeVisitorFromQueue();
        verticalCoaster.removeVisitorFromQueue();
        verticalCoaster.removeVisitorFromQueue();
        verticalCoaster.removeVisitorFromQueue();
        System.out.println("============================================================================================================\n");
    }

    // Part4A: Ride History Demo (Dream Carousel)
    public void partFourA() {
        System.out.println("==================================== Part4A: Ride History Management (Dream Carousel) ====================================");
        Employee carouselOperator = new Employee(
                "CL-EMP-009",
                "Sister Li",
                28,
                "Carousel Specialist",
                "CL-RIDE-002"
        );
        System.out.println(" " + carouselOperator.introduceYourself());

        Ride carousel = new Ride(
                "CL-RIDE-002",
                "Dream Carousel",
                carouselOperator,
                12
        );
        System.out.println(" Ride Info: " + carousel);

        Visitor v1 = new Visitor("CL-VIS-007", "Zhang Xiaobao", 6, "Child Visitor", LocalDate.of(2025, 12, 2));
        Visitor v2 = new Visitor("CL-VIS-008", "Li Xiaoya", 5, "Child Visitor", LocalDate.of(2025, 12, 2));
        Visitor v3 = new Visitor("CL-VIS-009", "Wang Dad", 35, "Family Visitor", LocalDate.of(2025, 12, 2));
        Visitor v4 = new Visitor("CL-VIS-010", "Zhao Mom", 33, "Family Visitor", LocalDate.of(2025, 12, 2));
        Visitor v5 = new Visitor("CL-VIS-011", "Chen Lele", 7, "Child Visitor", LocalDate.of(2025, 12, 2));

        System.out.println("\n Adding visitors to ride history:");
        carousel.addVisitorToHistory(v1);
        carousel.addVisitorToHistory(v2);
        carousel.addVisitorToHistory(v3);
        carousel.addVisitorToHistory(v4);
        carousel.addVisitorToHistory(v5);

        System.out.println("\n History verification (using return values):");
        boolean exists = carousel.checkVisitorFromHistory(v3);
        if (exists) {
            System.out.println(" Visitor [" + v3.getFullName() + "] eligible for membership point redemption");
        }

        boolean notExists = carousel.checkVisitorFromHistory(new Visitor("CL-VIS-999", "Test Visitor", 20, "General Visitor", LocalDate.now()));
        if (!notExists) {
            System.out.println("ℹ  Test visitor has not experienced this ride - recommended for priority experience～");
        }

        System.out.println("\n History statistics (using return values):");
        int historyCount = carousel.numberOfVisitors();
        System.out.println(" Today's popularity of " + carousel.getRideName() + ": " + (historyCount > 5 ? "High" : "Moderate"));

        carousel.printRideHistory();
        System.out.println("============================================================================================================\n");
    }

    // Part4B: History Sorting Demo (Ten-Loop Coaster)
    public void partFourB() {
        System.out.println("==================================== Part4B: Ride History Sorting (Ten-Loop Coaster) ====================================");
        Employee tenLoopOperator = new Employee(
                "CL-EMP-010",
                "Mr. Zhang",
                35,
                "Ten-Loop Coaster Specialist",
                "CL-RIDE-003"
        );
        System.out.println(" " + tenLoopOperator.introduceYourself());

        Ride tenLoopCoaster = new Ride(
                "CL-RIDE-003",
                "Ten-Loop Coaster",
                tenLoopOperator,
                6
        );
        System.out.println(" Ride Info: " + tenLoopCoaster);

        System.out.println("\n Adding visitors to ride history:");
        tenLoopCoaster.addVisitorToHistory(new Visitor("CL-VIS-012", "Liu Yang", 22, "General Visitor", LocalDate.of(2025, 12, 3)));
        tenLoopCoaster.addVisitorToHistory(new Visitor("CL-VIS-013", "Huang Li", 28, "VIP Visitor", LocalDate.of(2025, 12, 2)));
        tenLoopCoaster.addVisitorToHistory(new Visitor("CL-VIS-014", "Zhou Ming", 33, "Family Visitor", LocalDate.of(2025, 12, 2)));
        tenLoopCoaster.addVisitorToHistory(new Visitor("CL-VIS-015", "Wu Ting", 25, "Fast Pass Visitor", LocalDate.of(2025, 12, 3)));
        tenLoopCoaster.addVisitorToHistory(new Visitor("CL-VIS-016", "Zheng Tao", 30, "General Visitor", LocalDate.of(2025, 12, 1)));

        System.out.println("\n Ride history before sorting:");
        tenLoopCoaster.printRideHistory();

        System.out.println("\n Executing history sorting...");
        tenLoopCoaster.sortRideHistory();

        System.out.println("\n Ride history after sorting:");
        tenLoopCoaster.printRideHistory();
        System.out.println("============================================================================================================\n");
    }

    // Part5: Operation Cycle Demo (Super Swing)
    public void partFive() {
        System.out.println("==================================== Part5: Ride Operation Cycles (Super Swing) ====================================");
        Employee swingOperator = new Employee(
                "CL-EMP-011",
                "Mr. Wang",
                29,
                "Super Swing Specialist",
                "CL-RIDE-004"
        );
        System.out.println(" " + swingOperator.introduceYourself());

        Ride superSwing = new Ride(
                "CL-RIDE-004",
                "Super Swing",
                swingOperator,
                4
        );
        System.out.println(" Ride Info: " + superSwing);

        System.out.println("\n 10 visitors joining queue:");
        for (int i = 1; i <= 10; i++) {
            String visitorId = "CL-VIS-0" + (20 + i);
            String visitorName = "Visitor" + (20 + i);
            int age = 18 + (i % 20);
            String visitorType = (i % 3 == 0) ? "VIP Visitor" : (i % 4 == 0) ? "Fast Pass Visitor" : "General Visitor";
            superSwing.addVisitorToQueue(new Visitor(visitorId, visitorName, age, visitorType, LocalDate.of(2025, 12, 4)));
        }

        System.out.println("\n Queue status before operation:");
        superSwing.printQueue();

        System.out.println("\n Starting Super Swing Cycle 1...");
        superSwing.runOneCycle();

        System.out.println("\n Queue status after operation:");
        superSwing.printQueue();

        System.out.println("\n Ride history after operation:");
        superSwing.printRideHistory();
        System.out.println("============================================================================================================\n");
    }

    // Part6: History Export Demo (Giant Ferris Wheel)
    public void partSix() {
        System.out.println("==================================== Part6: Ride History Export (Giant Ferris Wheel) ====================================");
        Employee ferrisWheelOperator = new Employee(
                "CL-EMP-012",
                "Mr. Qin",
                32,
                "Ferris Wheel Specialist",
                "CL-RIDE-005"
        );
        System.out.println(" " + ferrisWheelOperator.introduceYourself());

        Ride ferrisWheel = new Ride(
                "CL-RIDE-005",
                "Giant Ferris Wheel",
                ferrisWheelOperator,
                6
        );
        System.out.println(" Ride Info: " + ferrisWheel);

        System.out.println("\n Adding 5 visitors to ride history:");
        ferrisWheel.addVisitorToHistory(new Visitor("CL-VIS-031", "You Jia", 24, "General Visitor", LocalDate.of(2025, 12, 5)));
        ferrisWheel.addVisitorToHistory(new Visitor("CL-VIS-032", "Xu Qing", 26, "VIP Visitor", LocalDate.of(2025, 12, 5)));
        ferrisWheel.addVisitorToHistory(new Visitor("CL-VIS-033", "He Wei", 29, "Family Visitor", LocalDate.of(2025, 12, 5)));
        ferrisWheel.addVisitorToHistory(new Visitor("CL-VIS-034", "Lv Yang", 31, "Fast Pass Visitor", LocalDate.of(2025, 12, 5)));
        ferrisWheel.addVisitorToHistory(new Visitor("CL-VIS-035", "Shi Ran", 27, "General Visitor", LocalDate.of(2025, 12, 5)));

        String exportPath = "cl_ferris_wheel_history.csv";
        System.out.println("\n Exporting ride history to file: " + exportPath);
        ferrisWheel.exportRideHistory(exportPath);

        System.out.println("\n Details of history to be exported:");
        ferrisWheel.printRideHistory();
        System.out.println("============================================================================================================\n");
    }

    // Part7: History Import Demo (Giant Ferris Wheel)
    public void partSeven() {
        System.out.println("==================================== Part7: Ride History Import (Giant Ferris Wheel) ====================================");
        Employee ferrisWheelOperator = new Employee(
                "CL-EMP-012",
                "Mr. Qin",
                32,
                "Ferris Wheel Specialist",
                "CL-RIDE-005"
        );
        System.out.println(" " + ferrisWheelOperator.introduceYourself());

        Ride ferrisWheelImport = new Ride(
                "CL-RIDE-005",
                "Giant Ferris Wheel",
                ferrisWheelOperator,
                6
        );
        System.out.println(" Ride Info: " + ferrisWheelImport);
        System.out.println("  Visitor count before import: " + ferrisWheelImport.numberOfVisitors());

        String importPath = "cl_ferris_wheel_history.csv";
        System.out.println("\n Importing ride history from file: " + importPath);
        ferrisWheelImport.importRideHistory(importPath);

        System.out.println("\n Import result verification:");
        System.out.println(" Visitor count after import:");
        ferrisWheelImport.numberOfVisitors();

        System.out.println("\n Details of imported ride history:");
        ferrisWheelImport.printRideHistory();
        System.out.println("============================================================================================================\n");
    }
}