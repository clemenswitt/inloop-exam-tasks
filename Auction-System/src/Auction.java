import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Auction {
    private boolean closed;
    private List<Person> bidders;
    private List<Item> allItems;

    public Auction() {
        closed = false;
        bidders = new ArrayList<>();
        allItems = new ArrayList<>();
    }

    public void addBid(Item bidItem, String nameOfBidder, long price) {
        if(closed) {
            throw new IllegalStateException("Auction is closed.");
        }
        if(bidItem == null || nameOfBidder == null) {
            throw new NullPointerException("BidItem & NameOfBidder must not be null.");
        }
        if(price <= 0 || nameOfBidder.isEmpty()) {
            throw new IllegalArgumentException("Price must be > 0. Name of bidder must be given.");
        }

        // Korrespondierenden Bidder zu nameOfBidder finden
        boolean bidderExists = false;
        Person bidder = null;
        for(Person person : bidders) {
            if(person.getName().equals(nameOfBidder)) {
                bidder = person;
                bidderExists = true;
            }
        }
        if(!bidderExists) {
            bidder = new Person(nameOfBidder);
        }
        bidders.add(bidder);

        boolean itemExists = false;
        for(Item item : allItems) {
            if(item.getName().equals(bidItem.getName())) {
                item.addBid(bidder, price);
                itemExists = true;
            }
        }
        if(!itemExists) {
            throw new NoSuchElementException("Item existiert nicht.");
        }
    }

    public String closeAuction() {
        if(closed) {
            throw new IllegalStateException("Auktion bereits geschlossen.");
        }
        closed = true;
        return generateItemListString();
    }

    public String generateAllBidsString(Item item) {
        String out = "All bids:";
        for(Bid bid : item.getAllBids()) {
            out += "\n" + bid.toString();
        }
        return out;
    }

    public String generateItemListString() {
        String out = "";
        for(Item item : allItems) {
            out += generateItemString(item) + "\n";
        }
        return out;
    }

    public void registerItem(Item item) {
        if(closed) {
            throw new IllegalStateException("Auktion bereits geschlossen.");
        }
        if(item == null) {
            throw new NullPointerException("Item must not be null.");
        }

        for(Item i : allItems) {
            if(i.getName().equals(item.getName()) || i.getDescription().equals(item.getDescription())) {
                throw new IllegalArgumentException("Item already registered.");
            }
        }
        allItems.add(item);
    }

    public abstract String generateItemString(Item item);

    public List<Item> getAllItems() {
        return allItems;
    }
}
