import java.util.HashMap;
import java.util.Map;

public class Surface extends RenovationObject {
    private double length;
    private double width;

    private Material selectedMaterial;

    public Surface(double length, double width) {

        if(length <= 0 || width <= 0) {
            throw new IllegalArgumentException("Illegal width or length");
        }

        this.length = length;
        this.width = width;
    }

    public void setMaterial(Material material) {
        if(material == null) {
            throw new NullPointerException("Material must not be null.");
        }
        this.selectedMaterial = material;
    }

    public double getArea() {
        return length * width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getPrice() {
        return selectedMaterial.getPriceOfASurface(this);
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

        Map<String, Integer> copiedMaterialMap = new HashMap<>(materials);

        String materialName = selectedMaterial.getName();
        int materialQuantityToAdd = selectedMaterial.getMaterialRequirements(this);

        if(copiedMaterialMap.containsKey(materialName)) {
            int currentMaterialQuantity = materials.get(materialName);
            copiedMaterialMap.replace(materialName, currentMaterialQuantity + materialQuantityToAdd);
        } else {
            copiedMaterialMap.put(materialName, materialQuantityToAdd);
        }

        return copiedMaterialMap;
    }


}
