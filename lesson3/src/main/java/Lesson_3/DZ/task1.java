package Lesson_3.DZ;

import java.io.*;
import java.util.ArrayList;

public class task1 {

    String path;
    ArrayList<Integer> arr;

    public task1(String path){
        this.path = path;
        System.out.println();
    }

    void ReadToArray(){
        arr = new ArrayList<>();
        try (FileInputStream in = new FileInputStream(path)) {
            int x;

            while ((x = in.read()) != -1) {
                arr.add(x);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void Print(){
        for (Integer el:arr) {
            System.out.println(el);
        }
    }
}
