public class Sale {
    private long preDiscountTotal;
    private ISalePricing pricing;

    public Sale(long preDiscountTotal, ISalePricing pricing) {
        if(pricing == null) {
            throw new NullPointerException("Pricing-Argument darf nicht null sein.");
        }

        if(preDiscountTotal < 0) {
            throw new IllegalArgumentException("preDiscountTotal darf nicht <0 sein.");
        }

        this.preDiscountTotal = preDiscountTotal;
        this.pricing = pricing;
    }

    public long getPreDiscountTotal() {
        return preDiscountTotal;
    }

    public void setPricing(ISalePricing pricing) {
        if(pricing == null) {
            throw new NullPointerException("Pricing darf nicht null sein");
        }

        this.pricing = pricing;
    }

    public long getTotal() {
        return pricing.getTotal(this);
    }

    public static ISalePricing createPricing(DiscountType discountType, double percentage, long discount, long threshold) {

        if(discountType == null) {
            throw new NullPointerException("discountType darf nicht null sein");
        }

        switch (discountType) {
            case ABSOLUTEDISCOUNT:
                return new AbsoluteDiscountPricing(discount, threshold);
            case PERCENTAGEDISCOUNT:
                return new PercentageDiscountPricing(percentage);
            default:
                return null;
        }
    }



}
