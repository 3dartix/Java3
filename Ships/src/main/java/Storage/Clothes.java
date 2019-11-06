package Storage;

public class Clothes extends Products {
    String name = "Одежда";
    public Clothes (int units){
        this.units = units;
    }

    @Override
    public String PrintProduct() {
        return name + ": " + this.units;
    }
}
