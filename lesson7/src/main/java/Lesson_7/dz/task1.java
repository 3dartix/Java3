package Lesson_7.dz;

public class task1 {
    public static void main(String[] args) {
        test(4, 3);
        System.out.println("\n");
        test(3, 5);
        System.out.println("\n");
        test(3, 3);
    }

    public static void test(int x, int y){
        int i = 0;
        int j = y-1;

        int[][] arr = new int[x][y];
        int number = 0;

        while(number != x * y) {
            //первый ряд
            for (int k = 0; k < y; k++) {
                if(arr[i][k] == 0) {
                    number++;
                    arr[i][k] = number;
                }
            }
            //второй ряд
            for (int k = 0; k < x; k++) {
                if(arr[k][j] == 0) {
                    number++;
                    arr[k][j] = number;
                }
            }
            //третий ряд
            for (int k = y-1; k >= 0; k--) {
                if(arr[j+x-y][k] == 0) {
                    number++;
                    arr[j+x-y][k] = number;
                }
            }
            //четверный ряд
            for (int k = x-1; k >= 0; k--) {
                if(arr[k][i] == 0) {
                    number++;
                    arr[k][i] = number;
                }
            }
            //уменьшаем итерацию
            i++;
            j--;
        }

        //печатаем
        for (int k = 0; k < x; k++) {
            for (int r = 0; r < y; r++) {
                System.out.print(arr[k][r] + "\t");
            }
            System.out.print("\n");
        }
    }
}
