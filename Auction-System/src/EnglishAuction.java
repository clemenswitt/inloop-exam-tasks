public class EnglishAuction extends Auction {
    @Override
    public String generateItemString(Item item) {
        if(item == null) {
            throw new NullPointerException("Item must ot be null.");
        }

        String out = item.toString();
        if(item.getAllBids().isEmpty()) {
            out += "\nNo bids placed";
        } else {
            out += "\nHighest bid: " + item.getHighestBid().toString();
        }
        return out;
    }
}
