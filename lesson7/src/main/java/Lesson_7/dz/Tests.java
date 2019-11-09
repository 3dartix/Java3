package Lesson_7.dz;


import Lesson_7.dz.annotation.AfterSuite;
import Lesson_7.dz.annotation.BeforeSuite;
import Lesson_7.dz.annotation.Test;

public class Tests {
    public static DzStudent dzStudent = null;

    @BeforeSuite
    public static void start() {
        System.out.println("Запускаем тест!");
        dzStudent = new DzStudent();
    }

    @Test(priority = 1)
    public static void Test1(){
        //метод вычисляющий выражение a * (b + (c / d)) и
        //возвращающий результат, где a,b,c,d – входные параметры этого метода.
        if(8 == dzStudent.calculate(2, 2, 4,2)){
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName() + " is working.");
        } else {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName() + " is failed.");
        }
    }

    @Test(priority = 2)
    public static void Test2(){
        //Написать метод, принимающий на вход два числа, и проверяющий
        //что их сумма лежит в пределах 10 до 20, если да – вернуть true,
        if(!dzStudent.checkTwoNumbers(20,5)){
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName() + " is working.");
        } else {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName() + " is failed.");
        }
    }

    @Test(priority = 3)
    public static void Test3(){
         //Написать метод, которому в качестве параметра передаётся
         //целое число, метод должен вернуть true если число отрицательное
        if(dzStudent.isNegative(-3)){
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName() + " is working.");
        } else {
            System.out.println(new Object(){}.getClass().getEnclosingMethod().getName() + " is failed.");
        }
    }

    @AfterSuite
    public static void end() {
        System.out.println("Закончили тестирование!");
    }

}
