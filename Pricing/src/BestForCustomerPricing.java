public class BestForCustomerPricing extends ComplexPricing {
    public BestForCustomerPricing(ISalePricing pricing) {
        super(pricing);
        if(pricing == null) {
            throw new NullPointerException("Pricing darf nicht null sein.");
        }
    }

    @Override
    public long getTotal(Sale sale) {
        // Initialisierungswert -> wird garantiert unterschritten, da alle Preise in durch getPricings() zurückgegebenes Array rabattiert.
        long bfcPricing = sale.getPreDiscountTotal();

        for (ISalePricing p : getPricings()) {
            if(p.getTotal(sale) < bfcPricing) {
                bfcPricing = p.getTotal(sale);
            }
        }

        return bfcPricing;
    }
}
