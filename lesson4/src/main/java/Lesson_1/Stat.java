package Lesson_1;

public class Stat<T extends Number> {
    private T[] nums;

    public Stat(T[] nums) {
        this.nums = nums;
    }

    public double avg() {
        double sum = 0.0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i].doubleValue();
        }

        return sum / nums.length;
    }

    public boolean sameAvg(Stat<?> another) {
        return Math.abs(this.avg() - another.avg()) < 0.00001;
    }

}

class MainStats {
    public static void main(String[] args) {
        Integer[] inums = {1,2,3,4,5};
        Double[] idouble = {1.0, 2.5, 3.0, 4.0, 5.0};

        Stat<Integer> iob = new Stat<>(inums);

        Stat<Double> iod = new Stat<>(idouble);

        System.out.println(iob.sameAvg(iod));


//        double res1 = iob.avg();
//        double res2 = iod.avg();
//
//        System.out.println(res1 + " " + res2);
    }
}
