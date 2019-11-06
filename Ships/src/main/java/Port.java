import Storage.Products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Port extends Stage {
    private Semaphore smp = new Semaphore(1);
    String portName; // A - забираем товары B - куда отвозим товары
    List<Products> products;

    public Port (String portName, Products...products){
        this.products = Arrays.asList(products);
        this.portName = portName;
        this.description = "в порту";
    }

    @Override
    public void go(Ship s) {
        String str1;
        String str2;
        String str3;

        //костыли, как-то нужно было определять в какой порт мы приплыли
        if(portName == "A") {
            str1 = " начинает погрузку ";
            str2 = " заканчивает погрузку ";
            str3 = " загрузил ";
            System.out.println(s.getName() + str1 + description + " "+ portName + ".");
            try {
                smp.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Upload(s);
            isThereProductsInPort();
        } else {
            str1 = " начинает выгрузку ";
            str2 = " заканчивает выгрузку ";
            str3 = " выгрузил ";
            System.out.println(s.getName() + str1 + description + " "+ portName + ".");
            Unload(s);
        }

        try {
            Thread.sleep(length / s.getSpeed() * 1000);
            System.out.println(s.getName() + str2 + description  + " "+ portName + ".");
            System.out.println(s.getName() + str3 + s.getProduct().PrintProduct());
            PrintProducts();
            smp.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void PrintProducts(){
        String srt = "";
        for (Products el: products) {
            srt = srt + " " + el.PrintProduct();
        }
        System.out.println("Продуктов в порту " + portName + ": " + srt);
    }

    //метод проверяет остались ли товары, кот нужно перевезти, если нет,
    // то изменяет Main.isThereProductsInPort = false; и цикл while по возвращению корабля
    // в порт А прерывается и корабль никуда не плывет
    void isThereProductsInPort(){
        boolean isThereProductsInPort = false;
        for (Products prodInPort: products) {
            if (prodInPort.getUnits() > 0) {
                isThereProductsInPort = true;
                break;
            }
        }
        if(!isThereProductsInPort) {
            Main.isThereProductsInPort = false;
        }
    }

    //загрузить товары
    public void Upload (Ship ship){
        //перебираем список с товарами и если не 0 грузим на корабль
        for (Products prodInPort: products) {
            if (prodInPort.getUnits() > 0) {
                //устанавливаем тип продукта, кот будем перевозить
                ship.setProduct(prodInPort);
                //количество единиц продукта
                if (prodInPort.getUnits() > ship.getLimit()) { // если продук в порту больше, чем лимит на корабле
                    //добавляем товар кораблю
                    ship.getProduct().IncUnits(ship.getLimit());
                    //отнимаем товар из порта
                    prodInPort.DecUnits(ship.getLimit());
                } else {
                    ship.getProduct().IncUnits(prodInPort.getUnits());
                    prodInPort.DecUnits(prodInPort.getUnits());
                }
                break;
            }
        }
    }

    //выгрузить товары
    public void Unload(Ship ship){
        for (Products prodInPort: products) {
            if(prodInPort.getClass() == ship.getProduct().getClass()){
                prodInPort.IncUnits(ship.getProduct().getUnits());
                break;
            }
        }
    }

}
