package Lesson_3;

import java.io.Serializable;

public class Student extends Human implements Serializable{
    int id;
    String name;

    transient Book book;

    public Student(int id, String name) {
        //super(1);
        this.id = id;
        this.name = name;
    }

    public void info() {
        System.out.println(id + " " + name);
    }
}
