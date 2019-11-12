package Lesson_8;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CountTrees {
    public static Map<Character, Integer> list = new HashMap<>(); //вариант решения 1
    public static int[] array = new int[5];

    public static void main(String[] args) {
        start();
        start2();
    }

    static void start(){
        try (FileInputStream in = new FileInputStream("123/txt.txt")) {
            int x;
            byte[] arr = new byte[512];
            String str = "";
            while ((x = in.read(arr)) != -1) {
                str = new String(arr,0,x, "UTF-8").replaceAll("\\s+","");
            }

            for(char c : str.toCharArray()) {
                if(!list.containsKey(c)) {
                    list.put(c, 1);
                } else {
                    list.put(c, (list.get(c) + 1));
                }
            }
            System.out.println("result 1: ");
            for (Map.Entry<Character, Integer> entry : list.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void start2(){
        try (FileInputStream in = new FileInputStream("123/txt.txt")) {
            int x;
            byte[] arr = new byte[512];
            String str = "";
            while ((x = in.read(arr)) != -1) {
                str = new String(arr,0,x, "UTF-8").replaceAll("\\s+","");
            }

            for(char c : str.toCharArray()) {
                array[Character.getNumericValue(c)-1]++;
            }

            System.out.println("\nresult 2: ");
            for (int i = 0; i < array.length; i++) {
                System.out.println((i+1) + " - " + array[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
