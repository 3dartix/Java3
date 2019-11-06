package Storage;

public class Fuel extends Products {
    public String name = "Топливо";
    public Fuel (int units){
        this.units = units;
    }

    @Override
    public String PrintProduct() {
        return name + ": " + this.units;
    }
}
