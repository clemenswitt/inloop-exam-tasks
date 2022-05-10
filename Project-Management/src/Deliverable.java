import java.security.IdentityScope;
import java.time.LocalDate;
import java.util.logging.LoggingPermission;

public class Deliverable extends ProjectItem{
    private long materialCost;
    private double productionTime;
    private LocalDate date;

    public Deliverable(String name, String details, double rate, long materialCost, double productionTime, LocalDate date) {
        super(name, details, rate);

        if(materialCost < 0 || productionTime <= 0 || name.isEmpty() || details.isEmpty()) {
            throw new IllegalArgumentException("MaterialCost must be be >= 0, ProductionTime must be > 0, Name & Details must not be empty.");
        }
        if(date == null) {
            throw new NullPointerException("Date must not be null.");
        }

        this.materialCost = materialCost;
        this.productionTime = productionTime;
        this.date = date;
    }

    @Override
    public double getTimeRequired() {
        return productionTime;
    }

    @Override
    public long getMaterialCost() {
        return materialCost;
    }

    public LocalDate getDate() {
        return date;
    }
}
