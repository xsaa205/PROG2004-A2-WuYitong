package model;

import java.util.Objects;

/**
 * Part2要求：改造为抽象类，封装所有人员的公共属性与行为，不可实例化
 * 新增抽象方法introduceYourself()，强制子类实现
 */
public abstract class Person {
    // Part1原有属性（不变）
    private String id;         // 唯一标识（员工ID/访客ID）
    private String fullName;   // 姓名
    private int age;           // 年龄

    // Part1原有构造器（不变）
    public Person() {}

    public Person(String id, String fullName, int age) {
        this.id = id;
        this.fullName = fullName;
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("年龄必须在0-150之间，当前输入：" + age);
        }
    }

    // Part1原有Getter/Setter（不变）
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public int getAge() { return age; }
    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("年龄必须在0-150之间，当前输入：" + age);
        }
    }

    // -------------------------- Part2新增：抽象方法（强制子类实现） --------------------------
    public abstract String introduceYourself();

    // Part1原有toString/equals/hashCode（不变）
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