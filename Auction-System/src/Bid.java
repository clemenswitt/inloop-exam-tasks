public class Bid {
    private long price;
    private Person bidder;

    public Bid(Person bidder, long price) {
        if(bidder == null) throw new NullPointerException();
        if(price <= 0) throw new IllegalArgumentException();
        this.bidder = bidder;
        this.price = price;
    }

    public Person getBidder() {
        return bidder;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getPrice() + " EUR by " + getBidder().getName();
    }
}