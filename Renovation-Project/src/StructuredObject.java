import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StructuredObject extends RenovationObject {

    private Set<RenovationObject> parts;

    public StructuredObject() {
        parts = new HashSet<>();
    }

    public void add(RenovationObject renovationObject) {
        if(renovationObject == null) {
            throw new NullPointerException("RenovationObject must not be null");
        }
        parts.add(renovationObject);
    }

    public double getPrice() {
        double price = 0;
        for(RenovationObject object : parts) {
            price += object.getPrice();
        }
        return price;
    }

    @Override
    public Map<String, Integer> addMaterialRequirements(Map<String, Integer> materials) {

        if(materials == null) {
            throw new NullPointerException("Materials must not be null.");
        }

        materials.forEach((k,v) -> {
            if(k == null || v == null) {
                throw new NullPointerException("K,V must not be null.");
            }
        });

        for(RenovationObject renovationObject : parts) {
            materials = renovationObject.addMaterialRequirements(materials);
        }

        return materials;
    }



}
