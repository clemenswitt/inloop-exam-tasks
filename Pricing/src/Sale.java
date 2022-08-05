public class Sale {
    private long preDiscountTotal;
    private ISalePricing pricing;

    public Sale(long preDiscountTotal, ISalePricing pricing) {
        if(pricing == null) {
            throw new NullPointerException();
        }
        if(preDiscountTotal < 0) {
            throw new IllegalArgumentException();
        }

        this.preDiscountTotal = preDiscountTotal;
        this.pricing = pricing;
    }

    public long getPreDiscountTotal() {
        return preDiscountTotal;
    }

    public void setPricing(ISalePricing pricing) {
        if(pricing == null) {
            throw new NullPointerException();
        }
        this.pricing = pricing;
    }

    public long getTotal() {
        return pricing.getTotal(this);
    }

    public static ISalePricing createPricing(DiscountType discountType, double percentage, long discount, long threshold) {
        if(discountType == null) {
            throw new NullPointerException();
        }

        switch (discountType) {
            case ABSOLUTEDISCOUNT: return new AbsoluteDiscountPricing(discount, threshold);
            case PERCENTAGEDISCOUNT: return new PercentageDiscountPricing(percentage);
            default: return null;
        }
    }
}