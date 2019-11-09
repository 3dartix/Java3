package Lesson_7.dz;

public class DzStudent {
    /*
     * Написать метод вычисляющий выражение a * (b + (c / d)) и
     * возвращающий результат, где a,b,c,d – входные параметры этого метода.
     */
    public int calculate(int a, int b, int c, int d) {

        // Поскольку заданием не регламентировано какого типа должно быть
        // возвращаемое значение - создадим несколько методов
        return a * (b + (c / d));
    }

    public static float calculate(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    /*
     * Написать метод, принимающий на вход два числа, и проверяющий
     * что их сумма лежит в пределах 10 до 20, если да – вернуть true,
     * в противном случае – false.
     */
    public boolean checkTwoNumbers(int first, int second) {
        int sum = first + second;
        return sum <= 30 && sum >= 10;
    }

    /*
     * Написать метод, которому в качестве параметра передаётся целое число,
     * метод додлжен напечатать в консоль положительное-ли число передали
     * или отрицательное; Замечание: ноль считаем положительным числом.
     */
    public static void printIsPositive(int variable) {
        System.out.println("Your number is " + ((variable >= 0) ? "positive" : "negative") + "!");
    }

    /*
     * Написать метод, которому в качестве параметра передаётся
     * целое число, метод должен вернуть true если число отрицательное
     */
    public boolean isNegative(int variable) {
        return (variable < 0);
    }

    /*
     * Написать метод, которому в качестве параметра передаётся строка,
     * обозначающая имя, метод должен вывести в консоль сообщение "Привет, указанное_имя!"
     */
    public static void printWelocome(String name) {
        System.out.println("Привет, " + name + "!");
    }

    /*
     * Написать метод, который определяет является ли год високосным.
     * Каждый 4-й год является високосным, кроме каждого 100-го,
     * при этом каждый 400-й – високосный.
     */
    public static boolean isLeapYear(int year) {
        return (year % 100 != 0) && (year % 4 == 0) || (year % 400 == 0);
    }
}
