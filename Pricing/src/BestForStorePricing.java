public class BestForStorePricing extends ComplexPricing {
    public BestForStorePricing(ISalePricing pricing) {
        super(pricing);
    }

    public long getTotal(Sale sale) {
        long bestForStorePricing = 0;

        for(ISalePricing p : getPricings()) {
            if(p.getTotal(sale) > bestForStorePricing) {
                bestForStorePricing = p.getTotal(sale);
            }
        }

        return bestForStorePricing;
    }
}