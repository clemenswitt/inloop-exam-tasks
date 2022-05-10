public class BestForStorePricing extends ComplexPricing {
    public BestForStorePricing(ISalePricing pricing) {
        super(pricing);
        if(pricing == null) {
            throw new NullPointerException("Pricing darf nicht null sein.");
        }
    }

    @Override
    public long getTotal(Sale sale) {
        // Initialisierungswert -> wird garantiert überschritten, da alle Preise > 0.
        long bfsPricing = 0;
        for(ISalePricing p : getPricings()) {
            if(p.getTotal(sale) > bfsPricing) {
                bfsPricing = p.getTotal(sale);
            }
        }
        return bfsPricing;
    }
}
