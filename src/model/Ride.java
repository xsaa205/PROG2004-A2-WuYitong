package model;

import util.RideInterface;
import util.VisitorComparator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unused")
public class Ride implements RideInterface {
    private final String rideId;          // Ride ID
    private final String rideName;        // Ride name
    private Employee operator;            // Operator
    private final Queue<Visitor> waitingQueue;    // Waiting queue
    private final LinkedList<Visitor> rideHistory; // Ride history
    private final int maxRider;           // Max riders per cycle
    private int numOfCycles;              // Operation cycles today

    // Newly added: Default constructor
    public Ride() {
        // Final fields must be initialized; set default values to ensure object validity
        this.rideId = "DEFAULT-RIDE-000";    // Default ride ID
        this.rideName = "Default Ride";      // Default ride name
        this.operator = null;                // Default: no operator (can be set via setter later)
        this.maxRider = 6;                   // Default: 6 riders per cycle (consistent with the parameterized constructor)

        // Collection field initialization (consistent with the logic in the parameterized constructor)
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;                // Default: 0 operating cycles
    }

    // Original: Parameterized constructor
    public Ride(String rideId, String rideName, Employee operator, int maxRider) {
        this.rideId = rideId;
        this.rideName = rideName;
        this.operator = operator;
        this.maxRider = (maxRider >= 1) ? maxRider : 6; // Default to 6 riders if input is invalid

        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // Getters/Setters
    public String getRideId() { return rideId; }
    public String getRideName() { return rideName; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public int getNumOfCycles() { return numOfCycles; }

    // Part7: Import ride history
    public void importRideHistory(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println(" File path cannot be empty");
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println(" Data file not found: " + filePath);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            boolean skipHeader = true;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (skipHeader) {
                    skipHeader = false;
                    System.out.println("  Skipping header: " + line);
                    continue;
                }

                String[] data = line.split(",");
                if (data.length != 5) {
                    System.out.println("  Line " + lineNumber + " format error (columns≠5), skipping: " + line);
                    continue;
                }

                try {
                    String visitorId = data[0].trim();
                    String fullName = data[1].trim();
                    int age = Integer.parseInt(data[2].trim());
                    String visitorType = data[3].trim();
                    LocalDate visitDate = LocalDate.parse(data[4].trim());

                    Visitor importedVisitor = new Visitor(visitorId, fullName, age, visitorType, visitDate);
                    rideHistory.add(importedVisitor);
                    System.out.println(" Line " + lineNumber + ": Successfully imported visitor [" + fullName + "] (ID: " + visitorId + ")");
                } catch (NumberFormatException e) {
                    System.out.println("  Line " + lineNumber + " age format error, skipping: " + line);
                } catch (DateTimeParseException e) {
                    System.out.println("  Line " + lineNumber + " date format error (requires yyyy-MM-dd), skipping: " + line);
                } catch (IllegalArgumentException e) {
                    System.out.println("  Line " + lineNumber + " invalid data, skipping: " + line + ", reason: " + e.getMessage());
                }
            }

            System.out.println("\n Import completed! Total " + rideHistory.size() + " valid visitors imported to [" + rideName + "] ride history");
        } catch (IOException e) {
            System.out.println(" Import failed! Reason: " + e.getMessage());
        }
    }

    // Part3: Queue management methods
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingQueue.offer(visitor);
            System.out.println(" Visitor [" + visitor.getFullName() + "] added to [" + rideName + "] queue");
        } else {
            System.out.println(" Cannot add null visitor to queue");
        }
    }

    @Override
    public void removeVisitorFromQueue() {
        Visitor removed = waitingQueue.poll();
        if (removed != null) {
            System.out.println(" Visitor [" + removed.getFullName() + "] removed from queue");
        } else {
            System.out.println(" No visitors in queue");
        }
    }

    @Override
    public void printQueue() {
        System.out.println("\n [" + rideName + "] Queue Status (Waiting: " + waitingQueue.size() + " | Max per cycle: " + maxRider + "):");
        if (waitingQueue.isEmpty()) {
            System.out.println("   → No waiting visitors, ready for immediate experience～");
            return;
        }
        Iterator<Visitor> it = waitingQueue.iterator();
        int idx = 1;
        while (it.hasNext()) {
            Visitor v = it.next();
            System.out.printf("   %d. Name: %s | Type: %s | Visit Date: %s%n",
                    idx, v.getFullName(), v.getVisitorType(), v.getVisitDate());
            idx++;
        }
        if (rideName.contains("Vertical Coaster")) {
            System.out.println("    Note: Vertical Coaster features 90° drop, requires height >1.4m～");
        }
    }

    // Part4A: Ride history methods
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor);
            System.out.println(" Visitor [" + visitor.getFullName() + "] added to [" + rideName + "] history");
        } else {
            System.out.println(" Cannot add null visitor to history");
        }
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println(" Cannot check null visitor");
            return false;
        }
        boolean isExists = rideHistory.contains(visitor);
        System.out.println(" Visitor [" + visitor.getFullName() + "] history status for [" + rideName + "]: " + isExists);
        return isExists;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println(" [" + rideName + "] total history visitors: " + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n [" + rideName + "] Ride History (Total: " + rideHistory.size() + " visitors):");
        if (rideHistory.isEmpty()) {
            System.out.println("   → No visitors today");
            return;
        }
        Iterator<Visitor> it = rideHistory.iterator();
        int idx = 1;
        while (it.hasNext()) {
            Visitor v = it.next();
            System.out.printf("   %d. Name: %s | ID: %s | Type: %s | Date: %s | Age: %d%n",
                    idx, v.getFullName(), v.getId(), v.getVisitorType(), v.getVisitDate(), v.getAge());
            idx++;
        }
        if (rideName.contains("Ferris Wheel")) {
            System.out.println("    Tip: Giant Ferris Wheel offers panoramic park views, best at sunset～");
        } else if (rideName.contains("Carousel")) {
            System.out.println("    Tip: Dream Carousel is family-friendly, perfect for accompanying children～");
        }
    }

    // Part5: Operation cycle method
    @Override
    public void runOneCycle() {
        if (operator == null) {
            System.out.println(" [" + rideName + "] has no operator, cannot operate");
            return;
        }
        if (waitingQueue.isEmpty()) {
            System.out.println(" [" + rideName + "] queue is empty, cannot operate");
            return;
        }

        System.out.println("\n [" + rideName + "] Starting Cycle " + (numOfCycles + 1) + " (Max riders: " + maxRider + ")");
        System.out.println(" Operator " + operator.getFullName() + " performing safety checks...");
        int actualRiderCount = 0;
        while (actualRiderCount < maxRider && !waitingQueue.isEmpty()) {
            Visitor rider = waitingQueue.poll();
            addVisitorToHistory(rider);
            actualRiderCount++;
        }

        numOfCycles++;
        System.out.println(" [" + rideName + "] Cycle " + numOfCycles + " completed! Riders: " + actualRiderCount);
    }

    // Part6: Export history method
    @Override
    public void exportRideHistory(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println(" File path cannot be empty");
            return;
        }
        if (rideHistory.isEmpty()) {
            System.out.println(" [" + rideName + "] history is empty, no export needed");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("visitor_id,full_name,age,visitor_type,visit_date");
            writer.newLine();
            for (Visitor visitor : rideHistory) {
                String line = String.join(",",
                        visitor.getId(),
                        visitor.getFullName(),
                        String.valueOf(visitor.getAge()),
                        visitor.getVisitorType(),
                        visitor.getVisitDate().toString()
                );
                writer.write(line);
                writer.newLine();
            }
            System.out.println(" [" + rideName + "] history exported to: " + filePath);
        } catch (IOException e) {
            System.out.println(" Export failed! Reason: " + e.getMessage());
        }
    }

    // Part4B: Sorting method
    public void sortRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println(" History is empty, no sorting needed");
            return;
        }
        rideHistory.sort(new VisitorComparator());
        System.out.println(" [" + rideName + "] history sorted by [visit date ASC + visitor type DESC]");
    }

    @Override
    public String toString() {
        return "Ride{" +
                "ID='" + rideId + '\'' +
                ", Name='" + rideName + '\'' +
                ", Operator=" + (operator != null ? operator.getFullName() : "Unassigned") +
                ", Max Riders=" + maxRider +
                ", Cycles Today=" + numOfCycles +
                ", Queue Size=" + waitingQueue.size() +
                ", History Size=" + rideHistory.size() +
                '}';
    }
}