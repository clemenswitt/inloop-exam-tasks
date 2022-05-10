public class AbsoluteDiscountPricing implements ISalePricing {
    private long discount;
    private long threshold;

    public AbsoluteDiscountPricing(long discount, long threshold) {
        if(discount < 0 || threshold < 0) {
            throw new IllegalArgumentException("Discount oder Threshold dürfen nicht <0 sein.");
        }

        this.discount = discount;
        this.threshold = threshold;
    }

    @Override
    public long getTotal(Sale sale) {
        if(sale.getPreDiscountTotal() - discount > threshold) {
            return sale.getPreDiscountTotal() - discount;
        }
        return Math.min(sale.getPreDiscountTotal(), this.threshold);
    }
}
