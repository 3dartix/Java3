public class Bay extends Stage {
    public Bay(int length, String name) {
        this.length = length;
        this.description =  name + " " + length + " метров";
    }
    @Override
    public void go(Ship s) {
        try {
            System.out.println(s.getName() + " начал этап: " + description);
            Thread.sleep(length / s.getSpeed() * 100);
            System.out.println(s.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
