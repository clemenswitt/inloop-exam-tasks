public class BestForCustomerPricing extends ComplexPricing {
    public BestForCustomerPricing(ISalePricing pricing) {
        super(pricing);
    }

    public long getTotal(Sale sale) {
        if(sale == null) {
            throw new NullPointerException();
        }
        long bestForCustomerPricing = sale.getPreDiscountTotal();

        for(ISalePricing p : getPricings()) {
            if(p.getTotal(sale) < bestForCustomerPricing) {
                bestForCustomerPricing = p.getTotal(sale);
            }
        }

        return bestForCustomerPricing;
    }
}