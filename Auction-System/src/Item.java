import java.util.ArrayList;
import java.util.List;

public class Item {
    private String name;
    private String description;
    private long minPrice;
    private List<Bid> allBids;
    private Bid highestBid;

    public Item(String name, String description, long minPrice) {
        if(name == null || description == null) {
            throw new NullPointerException("Name & Description must not be null.");
        }
        if(minPrice <= 0 || name.isEmpty() || description.isEmpty()) {
            throw new IllegalArgumentException("MinPrice must be > 0. Name & Description must not empty.");
        }

        this.name = name;
        this.description = description;
        this.minPrice = minPrice;
        this.allBids = new ArrayList<>();
    }

    public void addBid(Person bidder, long price) {
        if(bidder == null) {
            throw new NullPointerException("Bidder must not be null.");
        }
        if(price <= 0) {
            throw new IllegalArgumentException("Price must be > 0.");
        }

        if(price >= minPrice && (highestBid == null || price > highestBid.getPrice())) {
            Bid bid = new Bid(bidder, price);
            allBids.add(bid);
            highestBid = bid;
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

    public String toString() {
        return this.name + ": " + this.description + " (minimum bidding price: " + this.minPrice + " EUR)";
    }
}
