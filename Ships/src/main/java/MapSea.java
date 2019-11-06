import java.util.ArrayList;
import java.util.Arrays;

public class MapSea {
    private ArrayList<Stage> stages; //этапы
    public ArrayList<Stage> getStages() { return stages; }
    public MapSea(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
