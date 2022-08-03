public abstract class Material {
    private String name;
    private double price;

    public Material(String name, double price) {
        if(name == null) throw new NullPointerException();
        if(name.isEmpty() || price <= 0) throw new IllegalArgumentException();
        this.price = price;
        this.name = name;
    }

    public double getPricePerUnit() {
        return price;
    }

    public String getName() {
        return name;
    }

    public double getPriceOfASurface(Surface surface) {
        if(surface == null) throw new NullPointerException();
        return price * getMaterialRequirements(surface);
    }

    public abstract int getMaterialRequirements(Surface surface);
}