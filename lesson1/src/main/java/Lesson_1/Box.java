package Lesson_1;

public class Box {
    private Object obj;

    public Box(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public void info() {
        System.out.println("obj: " + obj);
        System.out.println("type: " + obj.getClass());
    }
}

class BoxInt {
    private Integer obj;

    public BoxInt(Integer obj) {
        this.obj = obj;
    }

    public Integer getObj() {
        return obj;
    }

    public void setObj(Integer obj) {
        this.obj = obj;
    }

    public void info() {
        System.out.println("obj: " + obj);
        System.out.println("type: " + obj.getClass());
    }
}

class Main {
    public static void main(String[] args) {
//        Box box1 = new Box(1);
//        Box box2 = new Box("Hello");
//        //BoxInt box2 = new BoxInt(1);
//
//        int x = 10;
//
//        x += (Integer) box2.getObj() ;
//        //x += box2.getObj();
//
//       // box2.info();
//
//        System.out.println(x);

//        BoxUltimate<String> bus = new BoxUltimate<>("Hello");
//        BoxUltimate<Integer> bui = new BoxUltimate<>(1);

//        if (bus instanceof Integer) {
//            System.out.println("ящик");
//        }

//        TwoGen<Integer, String> twoGen = new TwoGen<>(555, "java");
//
//        twoGen.showTypes();

//        int x = 10;
//        x += bus.getObj() ;
//
//        System.out.println(x);

//        box1.info();
//        box2.info();
    }
}

interface ITest {

}