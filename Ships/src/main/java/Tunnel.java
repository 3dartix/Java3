import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore smp = new Semaphore(1);

    public Tunnel() {
        this.length = 80;
        this.description = "Пролив " + length + " метров";
    }

    @Override
    public void go(Ship s) {
        try {
            try {
                System.out.println(s.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire();
                System.out.println(s.getName() + " начал этап: " + description);
                Thread.sleep(length / s.getSpeed() * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(s.getName() + " закончил этап: " + description);
                smp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
