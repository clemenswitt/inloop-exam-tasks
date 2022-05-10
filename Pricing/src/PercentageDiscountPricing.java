public class PercentageDiscountPricing implements ISalePricing {
    private double percentage;

    public PercentageDiscountPricing(double percentage) {
        if(percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Ungültige Prozentangabe!");
        }

        this.percentage = percentage;
    }

    @Override
    public long getTotal(Sale sale) {
        return sale.getPreDiscountTotal() - (long)(sale.getPreDiscountTotal() * percentage / 100);
    }
}
