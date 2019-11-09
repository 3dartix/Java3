package Lesson_6.DZ;

import java.util.Arrays;

public class DZ {
    public int[] task2 (int[] arr){
        boolean contains = false;
        int[] result = new int[0];
        for (int i = arr.length - 1; i >= 0; i--) {
            if(arr[i] == 4) {
                result = Arrays.copyOfRange(arr, (i+1), arr.length);
                contains = true;
                break;
            }
        }

        if(contains){
            return result;
        } else {
            throw new RuntimeException();
        }
    }
    public static boolean task3 (int[] arr){
        boolean result = false;
        if((Arrays.stream(arr).anyMatch(x -> x == 1)) && (Arrays.stream(arr).anyMatch(x -> x == 4))) {
            result = true;
        }
        return result;
    }
}
