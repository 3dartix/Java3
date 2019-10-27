package Lesson_3.DZ.task4;

import java.io.Serializable;

public class obj implements Serializable {
    public int id;
    public String name;

    public obj(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void Info(){
        System.out.println(id + " " + name);
    }
}
