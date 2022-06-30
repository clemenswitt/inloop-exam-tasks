public class Event implements Comparable<Event> {
    private String title;
    private EventCategory category;

    public Event(String title, EventCategory category) {
        if(title == null || category == null) {
            throw new NullPointerException();
        }
        if(title.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public EventCategory getCategory() {
        return category;
    }

    @Override
    public int compareTo(Event o) {
        if(title.compareTo(o.getTitle()) != 0) {
            return title.compareTo(o.getTitle());
        }
        return category.compareTo(o.getCategory());
    }
}
