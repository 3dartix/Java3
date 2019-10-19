package dzLesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class ArraySwap<T> {
    public T[] SwapElemtn (T[] arr, int el1, int el2){
        try {
            T tempValue = arr[el1-1];
            arr[el1-1] = arr[el2-1];
            arr[el2-1] = tempValue;
        } catch (IndexOutOfBoundsException e){
            System.out.println("Элементы находятся за пределами массива");
        }
        return arr;
    }

    public void Print(T[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            str = str + arr[i] + " ";
        }
        System.out.println(str);
    }
}
class Transform<T>{
    public ArrayList<T> TransformToArrayList (T[] arr){
        return new ArrayList<T>(Arrays.asList(arr));
    }
}

class Task1 {
    public static void main(String[] args) {
        //Integer[] arr1 = {1,2,3,4,5};
        String[] arr1 = {"один", "два", "три", "четыре", "пять"};

        ArraySwap<String> swap = new ArraySwap();

        swap.Print(arr1);
        swap.SwapElemtn(arr1, 1,5);
        swap.Print(arr1);
    }
}
class Task2 {
    public static void main(String[] args) {
        //Integer[] arr1 = {1,2,3,4,5};
        String[] arr1 = {"один", "два", "три", "четыре", "пять"};
        Transform transform = new Transform();

        ArrayList<?> result = transform.TransformToArrayList(arr1);
        System.out.println(result.getClass());
    }
}
