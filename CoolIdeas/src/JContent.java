import java.util.HashSet;
import java.util.Set;

public abstract class JContent {
    private String title;
    private String description;

    private Set<ContentObserver> observers;

    public JContent(String title, String description) {
        if(title == null || description == null) {
            throw new NullPointerException();
        }
        if(title.isEmpty() || description.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.description = description;
        this.observers = new HashSet<>();
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        if(description == null) {
            throw new NullPointerException();
        }
        if(description.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.description = description;
        notifyAll(this);
    }

    public void setTitle(String title) {
        if(title == null) {
            throw new NullPointerException();
        }
        if(title.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        notifyAll(this);
    }

    public abstract String toString();

    public void addObserver(ContentObserver ob) {
        if(ob == null) throw new NullPointerException();
        observers.add(ob);
    }

    public void removeObserver(ContentObserver ob) {
        if(ob == null) throw new NullPointerException();
        observers.remove(ob);
    }

    public int countObservers() {
        return observers.size();
    }

    public void notifyAll(JContent content) {
        for(ContentObserver obs : observers) {
            obs.update((JTopic) content);
        }
    }
}
