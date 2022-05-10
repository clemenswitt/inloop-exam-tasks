public class Paint extends Material {
    private double limit = 0.02;
    private int numberOfCoats;
    private double squareMetersPerLiter;

    public Paint(String name, double price, int numberOfCoats, double squareMetersPerLiter) {
        super(name, price);

        if(numberOfCoats < 1 || squareMetersPerLiter <= 0) {
            throw new IllegalArgumentException("Illegal argument values.");
        }

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
        if(surface == null) {
            throw new NullPointerException("Surface must not be null.");
        }

        double volume = surface.getArea() * numberOfCoats / squareMetersPerLiter;

        int paintBuckets = (int) (volume / 0.5);

        if(volume % 0.500000000 >= limit) {
            paintBuckets += 1;
        }

        return paintBuckets;
    }
}
