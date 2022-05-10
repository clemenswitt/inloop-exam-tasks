public abstract class ProjectItem {
    private String name;
    private String details;
    private double rate;

    public ProjectItem(String name, String details, double rate) {

        if(name == null || details == null) {
            throw new NullPointerException("Arguments should not be zero");
        }
        if(rate < 0 || name.isEmpty() || details.isEmpty()) {
            throw new IllegalArgumentException("Rate must be > 0, Name & Details must not be empty");
        }

        this.name = name;
        this.details = details;
        this.rate = rate;
    }

    public void setDetails(String newDetails) {

        if(newDetails == null) {
            throw new NullPointerException("newDetails must not be null.");
        }

        this.details = newDetails;
    }

    public long getCostEstimate() {
        return Math.round(rate * getTimeRequired() + getMaterialCost());
    }

    public abstract double getTimeRequired();

    public abstract long getMaterialCost();
}
