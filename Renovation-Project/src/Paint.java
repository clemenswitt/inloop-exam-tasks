public class Paint extends Material {
    private double limit = 0.02;
    private int numberOfCoats;
    private double squareMetersPerLiter;

    public Paint(String name, double price, int numberOfCoats, double squareMetersPerLiter) {
        super(name, price);
        if(numberOfCoats < 1 || squareMetersPerLiter <= 0) throw new IllegalArgumentException();
        this.numberOfCoats = numberOfCoats;
        this.squareMetersPerLiter = squareMetersPerLiter;
    }

    public int getNumberOfCoats() {
        return numberOfCoats;
    }

    public double getSquareMetersPerLiter() {
        return squareMetersPerLiter;
    }

    @Override
    public int getMaterialRequirements(Surface surface) {
        if(surface == null) throw new NullPointerException();
        double liters = surface.getArea() * numberOfCoats / squareMetersPerLiter;
        int buckets = (int) (liters / 0.5);
        if(liters % 0.500000000000 >= limit) buckets++;
        return buckets;
    }
}