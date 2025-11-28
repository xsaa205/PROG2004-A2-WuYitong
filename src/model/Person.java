package model;

import java.util.Objects;

/**
 * Part1要求：抽象人员类，封装所有人员的公共属性与行为，不可实例化
 * 包含3个实例变量、默认/带参构造器、getter/setter、抽象方法
 */
public abstract class Person {
    // 3个公共实例变量（满足Part1"至少3个"要求）
    private String id;         // 唯一标识（员工ID/访客ID）
    private String fullName;   // 姓名
    private int age;           // 年龄

    // 1. 默认构造器（满足Part1要求）
    public Person() {}

    // 2. 带参构造器（初始化所有公共属性，添加合法性校验）
    public Person(String id, String fullName, int age) {
        this.id = id;
        this.fullName = fullName;
        // 年龄合法性校验：0-150岁，非法则抛异常
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("年龄必须在0-150之间，当前输入：" + age);
        }
    }

    // 3. Getter与Setter（封装属性，满足Part1要求）
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("年龄必须在0-150之间，当前输入：" + age);
        }
    }

    // 4. 抽象方法：所有子类必须实现"自我介绍"（体现多态）
    public abstract String introduceYourself();

    // 5. 重写toString：便于打印对象详情
    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                '}';
    }

    // 6. 重写equals与hashCode：按ID判断唯一性（为后续集合校验做准备）
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