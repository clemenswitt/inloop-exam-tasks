public class Factory {
    private Purchasing purchasing;
    private ReceivingStock receivingStock;

    public Factory(Purchasing purchasing, ReceivingStock receivingStock) {
        if(purchasing == null || receivingStock == null) {
            throw new NullPointerException("Purchasing & Receiving stock must not be null.");
        }

        this.purchasing = purchasing;
        this.receivingStock = receivingStock;
    }

    public Purchasing getPurchasing() {
        return purchasing;
    }

    public ReceivingStock getReceivingStock() {
        return receivingStock;
    }

    public static Part createPart(PartType partType, String id, String name) {
        if(id.isEmpty() || name.isEmpty()) {
            throw new IllegalArgumentException("ID & Name must not be empty.");
        }

        return switch (partType) {
            case RESOURCE -> new Resource(id, name);
            case COMPONENTS -> new Components(id, name);
            case SINGLE_COMPONENT -> new SingleComponent(id, name);
        };
    }
}
