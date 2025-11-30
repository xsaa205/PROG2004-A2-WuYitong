package util;

import model.Visitor;

public interface RideInterface {
    // Part3: Queue Management
    void addVisitorToQueue(Visitor visitor);
    void removeVisitorFromQueue();
    void printQueue();

    // Part4A: Ride History Management
    void addVisitorToHistory(Visitor visitor);

    // Precisely suppress unused return value warnings for individual methods
    @SuppressWarnings("unused")
    boolean checkVisitorFromHistory(Visitor visitor);

    @SuppressWarnings("unused")
    int numberOfVisitors();
    void printRideHistory();

    // Part5: Operation Cycle
    void runOneCycle();

    // Part6: History Export
    void exportRideHistory(String filePath);
}