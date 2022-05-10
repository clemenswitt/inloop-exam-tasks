public class Purchasing implements StockObserver {
    private ReceivingStock receivingStock;

    public Purchasing(ReceivingStock receivingStock) {
        if(receivingStock == null) {
            throw new NullPointerException("Receiving stock must not be null.");
        }

        this.receivingStock = receivingStock;
    }

    public void buy(Part part, int count) {
        if(part == null) {
            throw new NullPointerException("Part must not be null.");
        }
        if(count < 1) {
            throw new IllegalArgumentException("Illegal amount.");
        }
        receivingStock.insert(part, count);
    }

    public ReceivingStock getStock() {
        return receivingStock;
    }

    @Override
    public void onPartCountChanged(Part part, int count) {
        if(part == null) {
            throw new NullPointerException("Part must not be null.");
        }
        if(count < 1) {
            throw new IllegalArgumentException("Illegal amount.");
        }
        buy(part, receivingStock.getMaxStockItems() - count);
    }
}
