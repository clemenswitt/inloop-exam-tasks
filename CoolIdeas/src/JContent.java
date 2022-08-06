import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class JContent {
    private String title;
    private String description;
    private Set<ContentObserver> observers;

    public JContent(String title, String description) {
        if(title == null || description == null) throw new NullPointerException();
        if(title.isEmpty() || description.isEmpty()) throw new IllegalArgumentException();
        this.title = title;
        this.description = description;
        this.observers = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description == null) throw new NullPointerException();
        if(description.isEmpty()) throw new IllegalArgumentException();
        this.description = description;

        for(ContentObserver o : observers) {
            o.update(this);
        }
    }

    public void setTitle(String title) {
        if(title == null) throw new NullPointerException();
        if(title.isEmpty()) throw new IllegalArgumentException();
        this.title = title;

        for(ContentObserver o : observers) {
            o.update(this);
        }
    }

    public void addObserver(ContentObserver observer) {
        if(observer == null) throw new NullPointerException();
        observers.add(observer);
    }

    public void removeObserver(ContentObserver observer) {
        if(observer == null) throw new NullPointerException();
        observers.remove(observer);
    }

    public int countObservers() {
        return observers.size();
    }

    public abstract String toString();
}