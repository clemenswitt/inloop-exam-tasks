import java.util.Random;

public class VehicleGenerator {
    private Random randomGenerator;

    public VehicleGenerator() {
        this.randomGenerator = new Random();
    }

    public Vehicle createVehicle() {
        switch(randomGenerator.nextInt(3)) {
            case 1: return new Car();
            case 2: return new Bicycle();
            default: return new Bus();
        }
    }
}
