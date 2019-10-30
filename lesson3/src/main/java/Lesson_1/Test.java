package Lesson_1;


// 1
public class Test {
    {
        System.out.println("1");
    }

    public Test() {
        System.out.println("2");
    }

    static {
        System.out.println("3");
    }

    {
        System.out.println("4");
    }

    public static void main(String[] args) {
        new Test();
    }
}



// 2


//class A {
//    public void startProcess() {
//        System.out.println("a ");
//    }
//}
//
//class B extends A {
//    public void startProcess() throws IOException {
//        super.startProcess();
//        System.out.println("b ");
//        throw new IOException();
//    }
//
//    public static void main(String[] args) {
//        try {
//            new B().startProcess();
//        } catch (IOException e) {
//            System.out.println("Exception!!!");
//        }
//    }
//}








// 3

//class MainThree {
//    public static void main(String[] args) {
//        int i = 0;
//        System.out.println(++i == i++);
//        System.out.println(i++ == i++);
//    }
//}










// 4 Являются ли массивы в java однородными












// 5


//class TestFive {
//    public static void main(String[] args) {
//        boolean toBe = .....
//        PrinceOfJava prince = new PrinceOfJava();
//        prince.torturePrince((toBe || !toBe));
//    }
//}
//
//class PrinceOfJava {
//    void torturePrince(boolean mind) {
//        if (mind) {
//            System.out.println("i will hate everybody");
//        } else {
//            System.out.println("i like citizen");
//        }
//    }
//}






















// 6 В каком порядке должны размещаться следующие опертаторы


// 1 вариант
// Package statement
// Imports
// Class or interface definitions


// 2 вариант
// Imports
// Package statement
// Class or interface definitions

// 3 вариант
// в любом порядке