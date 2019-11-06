package Storage;

public class Food extends Products {
    String name = "Еда";
    public Food (int units){
        this.units = units;
    }

    @Override
    public String PrintProduct() {
        return name + ": " + this.units;
    }
}
