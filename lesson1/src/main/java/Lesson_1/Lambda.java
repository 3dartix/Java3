package Lesson_1;

import java.util.ArrayList;
import java.util.List;
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
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");

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
        items.forEach(item -> {
            if ("C".equals(item)) {
                System.out.println(item);
            }
        });
////
//        items.stream()
//                .filter(s->s.contains("B"))
//                .forEach(System.out::println);
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

class TestHey {
    public static void main(String[] args) {
        //Hey hey = new Hey();
        //int a = 10;
        Hey hey = new Hey(1,2,3);
        MultiHey multiHey = new MultiHey(hey);

        Function<Hey, Double> square = multiHey::square;

        double ans = square.apply(hey);
    }
}
