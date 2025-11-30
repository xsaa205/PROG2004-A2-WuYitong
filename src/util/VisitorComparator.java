package util;

import model.Visitor;
import java.util.Comparator;

/**
 * Implements Comparator<Visitor> to sort by at least 2 instance variables
 * Chimelong scenario adaptation: Sorting rules aligned with Chimelong operational priorities
 * (Fast Pass > VIP > Family > General, earlier visit dates take precedence)
 */
public class VisitorComparator implements Comparator<Visitor> {

    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 1. Primary sorting dimension: Ascending order by visit date (earlier visitors first, aligns with Chimelong's "first-come-first-serve" principle)
        int dateCompare = v1.getVisitDate().compareTo(v2.getVisitDate());
        if (dateCompare != 0) {
            return dateCompare; // Directly sort by date when dates differ
        }

        // 2. Secondary sorting dimension: Descending order by visitor type (same date: Fast Pass > VIP > Family > General)
        // Custom type weighting: String comparison for priority (higher weight strings rank first using v2.compareTo(v1) for descending)
        return getVisitorTypeWeight(v2.getVisitorType()) - getVisitorTypeWeight(v1.getVisitorType());
    }

    /**
     * Chimelong visitor type weights: Numerical values assigned to different types for sorting
     * @param visitorType Visitor type (e.g., "Fast Pass Visitor")
     * @return Corresponding weight (10=Fast Pass, 8=VIP, 5=Family, 3=General)
     */
    private int getVisitorTypeWeight(String visitorType) {
        return switch (visitorType) {
            case "Fast Pass Visitor" -> 10; // Chimelong Fast Pass highest priority
            case "VIP Visitor" -> 8;        // VIP next
            case "Family Visitor" -> 5;     // Family (with children) before General
            case "General Visitor" -> 3;    // General base priority
            default -> 1;                   // Unknown types lowest priority
        };
    }
}