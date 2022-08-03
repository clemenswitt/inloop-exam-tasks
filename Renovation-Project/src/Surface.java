import java.util.HashMap;
import java.util.Map;

public class Surface extends RenovationObject {
    private double length;
    private double width;
    private Material selectedMaterial;

    public Surface(double width, double length) {
        if(width <= 0 || length <= 0) throw new IllegalArgumentException();
        this.width = width;
        this.length = length;
    }

    public void setMaterial(Material material) {
        if(material == null) throw new NullPointerException();
        this.selectedMaterial = material;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double getPrice() {
        return selectedMaterial.getPriceOfASurface(this);
    }

    public double getArea() {
        return length * width;
    }

    @Override
    public Map<String, Integer> addMaterialRequirements(Map<String, Integer> materials) {
        Map<String, Integer> copyOfMaterials = new HashMap<>(materials);

        copyOfMaterials.forEach((k,v) -> {
            if(k == null || v == null) throw new NullPointerException();
        });

        int materialQty = selectedMaterial.getMaterialRequirements(this);
        String materialName = selectedMaterial.getName();

        if(copyOfMaterials.containsKey(materialName)) {
            int oldQty = copyOfMaterials.get(materialName);
            copyOfMaterials.replace(materialName, oldQty, oldQty + materialQty);
            return copyOfMaterials;
        } else {
            copyOfMaterials.put(materialName, materialQty);
            return copyOfMaterials;
        }
    }
}