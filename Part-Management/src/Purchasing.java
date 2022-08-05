public class Purchasing implements StockObserver {
    private ReceivingStock receivingStock;

    public Purchasing(ReceivingStock receivingStock) {
        if(receivingStock == null) {
            throw new NullPointerException();
        }
        this.receivingStock = receivingStock;
    }

    public ReceivingStock getStock() {
        return receivingStock;
    }

    public void buy(Part part, int count) {
        receivingStock.insert(part, count);
    }

    @Override
    public void onPartCountChanged(Part part, int count) {
        if(count < receivingStock.getMinStockItems()) {
            buy(part, receivingStock.getMaxStockItems() - count);
        }
    }
}