public class Flooring extends Material {
    private double limit = 0.02;
    private double widthOfFlooring;

    public Flooring(String name, double price, double width) {
        super(name, price);

        if(width <= 0) {
            throw new IllegalArgumentException("Illegal width argument.");
        }

        this.widthOfFlooring = width;
    }

    public double getWidth() {
        return widthOfFlooring;
    }

    @Override
    public int getMaterialRequirements(Surface surface) {
        if(surface == null) {
            throw new NullPointerException("Surface must not be null.");
        }

        int floorPanels = (int) (surface.getArea() / widthOfFlooring);

        if(surface.getArea() % widthOfFlooring >= limit) {
            floorPanels += 1;
        }

        return floorPanels;
    }
}
