package Lesson_1;

import java.util.*;
import java.util.function.Function;

public class Lambda {
    public static void main(String[] args) {

        Operationable operation;
        operation = (x, y) -> x + y;

        int result = operation.calculate(10, 20);
        System.out.println(result); //30
    }
}

interface Operationable {
    int calculate(int x, int y);
}

//public class LambdaApp {
//
//    public static void main(String[] args) {
//
//        Operationable op = new Operationable(){
//
//            public int calculate(int x, int y){
//
//                return x + y;
//            }
//        };
//
//        int z = op.calculate(20, 10);
//        System.out.println(z); // 30
//    }
//}
//
//interface Operationable{
//    int calculate(int x, int y);
//}


class TestL2 {
    public static void main(String[] args) {
//        List<String> items = new ArrayList<>();
//        items.add("A");
//        items.add("B");
//        items.add("C");
//        items.add("D");
//        items.add("E");

//
//        for (String item : items) {
//            if (item.equals("C")) {
//                System.out.println(item);
//            }
//            System.out.println(item);
//        }
//
//        // items.forEach(item->System.out.println(item));
////
//        items.forEach(item -> {
//            if ("C".equals(item)) {
//                System.out.println(item);
//            }
//        });
////
//        items.stream()
//                .filter(s->s.contains("B"))
//                .forEach(System.out::println);


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
            }
        });

        Thread t2 = new Thread(t1);

        t1.start();
        t2.start();
    }
}


class Hey {
//    public double square(double num, int a) {
//        return Math.pow(num, 2);
//    }

    int a;
    int b;
    int c;

    public Hey(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

class MultiHey {
    private Hey hey;

    public MultiHey(Hey hey) {
        this.hey = hey;
    }

    public double square(Hey hey) {
        return 1.0;
    }

}

class BoxL {
    int id;
    String name;

    public BoxL(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString()  {
        return id + " " + name;
    }
}

class TestHey {
    public static void main(String[] args) {
        List<BoxL> list = new LinkedList<>();

        list.add(new BoxL(1, "Test1"));
        list.add(new BoxL(2, "Test1"));
        list.add(new BoxL(3, "Test1"));
        list.add(new BoxL(4, "Test2"));
        list.add(new BoxL(5, "Test2"));
        list.add(new BoxL(6, "Test3"));
        list.add(new BoxL(7, "Test3"));
        list.add(new BoxL(8, "Test4"));

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        Map<String, List<Integer>> map = new HashMap<>();

        list.forEach(item -> map.computeIfAbsent(item.name, k -> new ArrayList<>()).add(item.id));

        System.out.println(map);

        //Hey hey = new Hey();
        //int a = 10;
//        Hey hey = new Hey(1,2,3);
//        MultiHey multiHey = new MultiHey(hey);
//
//        Function<Hey, Double> square = multiHey::square;
//
//        double ans = square.apply(hey);
    }
}
