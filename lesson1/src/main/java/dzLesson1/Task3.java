package dzLesson1;
//Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
//    Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
//    Для хранения фруктов внутри коробки можно использовать ArrayList;
//    Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
//    Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
//    Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
//    Не забываем про метод добавления фрукта в коробку.

import java.util.ArrayList;

class Fruit {
    float weight = 0;
}
class Apple extends Fruit {
    public Apple(float weight){
        this.weight = weight;
    }
}
class Orange extends Fruit {
    public Orange(float weight){
        this.weight = weight;
    }
}

class Box <T extends Fruit> {
    private ArrayList<T> fruits;

    public Box (){
        fruits = new ArrayList<T>();
    }

    public void FruitAdd(T fruit, int count){
        for (int i = 0; i < count; i++) {
            fruits.add(fruit);
        }
    }

    public float GetWeight() {
        float result = 0;
        for (T fruit: fruits) {
            result += fruit.weight;
        }
        return result;
    }

    public boolean Compare(Box<?> otherBox){
        boolean result;
        if(GetWeight() == otherBox.GetWeight()){
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public void ReplaceBox (Box<T> otherBox){
        otherBox.fruits = new ArrayList<T>(fruits);
        fruits.clear();
    }
}

public class Task3 {
    public static void main(String[] args) {
        Box<Fruit> boxFruit = new Box<>();
        Box<Fruit> boxFruit2 = new Box<>();
        boxFruit.FruitAdd(new Apple(1), 10);
        boxFruit.ReplaceBox(boxFruit2);

        System.out.println("коробка1 " + boxFruit.GetWeight());
        System.out.println("коробка2 " + boxFruit2.GetWeight());

        boxFruit.FruitAdd(new Orange(2), 30);
        System.out.println("коробка1 " + boxFruit.GetWeight());
        System.out.println("коробка2 " + boxFruit2.GetWeight());

    }
}
