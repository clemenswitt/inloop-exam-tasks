public class ReceivingStock extends Stock {
    private int minStockItems;
    private int maxStockItems;

    public ReceivingStock(int minStockItems, int maxStockItems) {
        if(minStockItems < 1 || maxStockItems <= minStockItems) {
            throw new IllegalArgumentException("minStockItems must be > 1. maxStockItems must be greater than minStockItems.");
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
        if(part == null) {
            throw new NullPointerException("Part must not be null.");
        }
        if(amount < 1) {
            throw new IllegalArgumentException("Illegal amount.");
        }
        if(amount > this.maxStockItems){
            return false;
        }

        return super.insert(part, amount);
    }

    public boolean remove(Part part, int amount) {
        if(part == null) {
            throw new NullPointerException("Part must not be null.");
        }
        if(amount < 1) {
            throw new IllegalArgumentException("Illegal amount.");
        }

        return super.remove(part, amount);
    }
}