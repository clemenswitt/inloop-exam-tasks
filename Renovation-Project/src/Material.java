public abstract class Material {
    private String name;
    private double price;

    public Material(String name, double price) {

        if(name == null) {
            throw new NullPointerException("Name must not be null.");
        }
        if(name.isEmpty() || price < 0) {
            throw new IllegalArgumentException("Illegal argument values.");
        }

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPricePerUnit() {
        return price;
    }

    public double getPriceOfASurface(Surface surface) {
        if(surface == null) {
            throw new NullPointerException("Surface must not be null.");
        }
        return price * getMaterialRequirements(surface);
    }

    abstract int getMaterialRequirements(Surface surface);
}
