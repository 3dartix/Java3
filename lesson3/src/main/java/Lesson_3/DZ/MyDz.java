package Lesson_3.DZ;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MyDz {
    public static void main(String[] args) {
//        task1 t1 = new task1("dz/dz1.txt");
//        t1.ReadToArray();
//        t1.Print();

//        task2 t2 = new task2();
//        try {
//            t2.MergeFiles("dz/task2/dz1.txt","dz/task2/dz2.txt","dz/task2/dz3.txt","dz/task2/dz4.txt","dz/task2/dz5.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        task3 t3 = null;
        try {
            t3 = new task3("dz/task3/file.txt");
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.println("\n");
                int a = scanner.nextInt();

                t3.SetPage(a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                t3.Close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
