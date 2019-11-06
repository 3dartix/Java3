package Storage;

public abstract class Products implements Cloneable {
    protected int units;

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void DecUnits(int units) {
        if(this.units > 0 && this.units >= units){
            this.units -= units;
        }
    }

    public void IncUnits(int units) {
        this.units += units;
    }

    public abstract String PrintProduct();

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
