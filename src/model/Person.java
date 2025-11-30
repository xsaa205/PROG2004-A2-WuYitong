package model;

import java.util.Objects;

@SuppressWarnings("unused") // Suppress unused setter warnings
public abstract class Person {
    private String id;         // Unique identifier (Employee ID/Visitor ID)
    private String fullName;   // Full name
    private int age;           // Age

    public Person() {}

    public Person(String id, String fullName, int age) {
        this.id = id;
        this.fullName = fullName;
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age must be between 0 and 150. Current input: " + age);
        }
    }

    // Getters/Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public int getAge() { return age; }
    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age must be between 0 and 150. Current input: " + age);
        }
    }

    // Abstract method (forces subclasses to implement)
    public abstract String introduceYourself();

    // Override utility methods
    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}