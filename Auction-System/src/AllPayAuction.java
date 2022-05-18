public class AllPayAuction extends Auction {
    @Override
    public String generateItemString(Item item) {
        String out = item.toString() + "\n";
        if(!item.getAllBids().isEmpty()){
            out = out + "Highest bid: " + item.getHighestBid().toString() + "\n" + generateAllBidsString(item);
        } else {
            out = out + "No bids placed";
        }
        return out;
    }
}
