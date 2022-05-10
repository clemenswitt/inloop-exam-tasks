import java.util.List;
import java.util.ArrayList;
public abstract class ComplexPricing implements ISalePricing {
    private List<ISalePricing> pricings = new ArrayList<>();

    public ComplexPricing(ISalePricing pricing) {
        if(pricing == null) {
            throw new NullPointerException("Pricing darf nicht null sein.");
        }
        pricings.add(pricing);
    }

    public void add(ISalePricing pricing) {
        if(pricing == null) {
            throw new NullPointerException("Pricing darf nicht null sein");
        }
        pricings.add(pricing);
    }

    @Override
    public long getTotal(Sale sale) {
        return 0;
    }

    public List<ISalePricing> getPricings() {
        return this.pricings;
    }
}
