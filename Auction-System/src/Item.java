import java.util.ArrayList;
import java.util.List;

public class Item {
    private String name;
    private String description;
    private long minPrice;
    private List<Bid> allBids;
    private Bid highestBid;

    public Item(String name, String description, long minPrice) {
        if(name == null || description == null) throw new NullPointerException();
        if(name.isEmpty() || description.isEmpty() || minPrice <= 0) throw new IllegalArgumentException();
        this.name = name;
        this.description = description;
        this.minPrice = minPrice;
        this.allBids = new ArrayList<>();
    }

    public void addBid(Person bidder, long price) {
        if(bidder == null) throw new NullPointerException();
        if(price <= 0) throw new IllegalArgumentException();
        if(price >= minPrice && (highestBid == null || price > highestBid.getPrice())) {
            Bid bid = new Bid(bidder, price);
            highestBid = bid;
            allBids.add(bid);
        }
    }

    public List<Bid> getAllBids() {
        return allBids;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Bid getHighestBid() {
       return highestBid;
    }

    @Override
    public String toString() {
        return name + ": " + description + " (minimum bidding price: " + minPrice + " EUR)";
    }
}
