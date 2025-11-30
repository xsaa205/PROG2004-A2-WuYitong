package model;

import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings("unused") // Suppress unused method/constructor warnings
public class Visitor extends Person {
    private String visitorType;    // Visitor type (e.g., "General Visitor"/"VIP Visitor")
    private LocalDate visitDate;   // Visit date

    public Visitor() {}

    public Visitor(String id, String fullName, int age, String visitorType, LocalDate visitDate) {
        super(id, fullName, age);
        this.visitorType = visitorType;
        this.visitDate = visitDate;
    }

    // Getter/Setter
    public String getVisitorType() { return visitorType; }
    public void setVisitorType(String visitorType) { this.visitorType = visitorType; }
    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    // Implement abstract method
    @Override
    public String introduceYourself() {
        return "[Visitor] " + getFullName() + " (ID: " + getId() + "), Type: " + visitorType + ", Visit Date: " + visitDate;
    }

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