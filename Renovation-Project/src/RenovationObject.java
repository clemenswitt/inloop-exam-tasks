import java.util.Map;

public abstract class RenovationObject {
    abstract double getPrice();
    abstract Map<String, Integer> addMaterialRequirements(Map<String, Integer> materials);
}
