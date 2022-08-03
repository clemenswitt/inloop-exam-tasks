public class ReceivingStock extends Stock {
    private int minStockItems;
    private int maxStockItems;

    public ReceivingStock(int minStockItems, int maxStockItems) {
        if(minStockItems < 0 || maxStockItems <= 0 || maxStockItems <= minStockItems) {
            throw new IllegalArgumentException();
        }
        this.minStockItems = minStockItems;
        this.maxStockItems = maxStockItems;
    }

    public int getMinStockItems() {
        return minStockItems;
    }

    public int getMaxStockItems() {
        return maxStockItems;
    }

    public boolean insert(Part part, int amount) {
        if(super.getCount(part) + amount > maxStockItems) return false;
        return super.insert(part, amount);
    }

    public boolean remove(Part part, int amount) {
        return super.remove(part, amount);
    }
}