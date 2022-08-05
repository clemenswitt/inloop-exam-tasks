public class Factory {
    private Purchasing purchasing;
    private ReceivingStock receivingStock;

    public Factory(Purchasing purchasing, ReceivingStock receivingStock) {
        if(purchasing == null || receivingStock == null) {
            throw new NullPointerException();
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
        if (partType == null || name == null || id == null) {
            throw new NullPointerException();
        }
        if(name.isEmpty() || id.isEmpty()) {
            throw new IllegalArgumentException();
        }

        switch(partType) {
            case COMPONENTS: return new Components(id, name);
            case SINGLE_COMPONENT: return new SingleComponent(id, name);
            case RESOURCE: return new Resource(id, name);
            default: return null;
        }
    }
}