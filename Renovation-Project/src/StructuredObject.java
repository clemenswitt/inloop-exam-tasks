import java.util.*;

public class StructuredObject extends RenovationObject {
    private Set<RenovationObject> parts;

    public StructuredObject() {
        this.parts = new HashSet<>();
    }

    public void add(RenovationObject renovationObject) {
        if(renovationObject == null) {
            throw new NullPointerException();
        }
        parts.add(renovationObject);
    }

    @Override
    public double getPrice() {
        double price = 0;
        for(RenovationObject obj : parts) {
            price += obj.getPrice();
        }
        return price;
    }

    @Override
    public Map<String, Integer> addMaterialRequirements(Map<String, Integer> materials) {
        if(materials == null) {
            throw new NullPointerException();
        }

        materials.forEach((k,v) -> {
            if(k == null || v == null) throw new NullPointerException();
        });

        for(RenovationObject obj: parts) {
            materials = obj.addMaterialRequirements(materials);
        }

        return  materials;
    }
}