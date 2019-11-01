package Lesson_3.DZ;

import java.io.Serializable;

public class Studens implements Serializable {
    int id;
    String name;
    public Studens(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void info() {
        System.out.println(id + " " + name);
    }
}