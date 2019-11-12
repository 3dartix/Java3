package dzLesson1;

import java.util.*;

class SomeClass {
    int id;
    String name;

    public SomeClass(int id, String name){
        this.id = id;
        this.name = name;
    }
}

public class Task4 {
    public static void main(String[] args) {
        List<SomeClass> list = new ArrayList<>();
        list.add(new SomeClass(1, "Test1"));
        list.add(new SomeClass(2, "Test1"));
        list.add(new SomeClass(3, "Test1"));
        list.add(new SomeClass(4, "Test2"));
        list.add(new SomeClass(5, "Test2"));
        list.add(new SomeClass(6, "Test3"));
        list.add(new SomeClass(7, "Test3"));
        list.add(new SomeClass(8, "Test4"));

        HashMap<String, List<Integer>> hashMap = new HashMap<String, List<Integer>>();

        for (SomeClass el:list) {
            if(hashMap.get(el.name) == null){
                hashMap.put(el.name, new ArrayList<>());
                hashMap.get(el.name).add(el.id);
            } else {
                hashMap.get(el.name).add(el.id);
            }
        }

        hashMap.forEach((key, arr) -> {
            System.out.println(key + ": " + arr);
        });
    }
}
