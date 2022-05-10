import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Stock {
    private Map<Part, Integer> parts;
    private List<StockObserver> observers;

    public Stock() {
        this.parts = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    public int getCount(Part part) {
        if(part == null) {
            throw new NullPointerException("Part must not be null.");
        }

        return parts.getOrDefault(part, -1);
    }

    public boolean insert(Part part, int amount) {
        if(part == null) {
            throw new NullPointerException("Part must not be null.");
        }
        if(amount < 1) {
            throw new IllegalArgumentException("Illegal amount.");
        }

        if(parts.containsKey(part)) {
            parts.replace(part, parts.get(part) + amount);
        } else {
            parts.put(part, amount);
        }

        return true;
    }

    public boolean remove(Part part, int amount) {
        if(part == null) {
            throw new NullPointerException("Part must not be null.");
        }
        if(amount < 1) {
            throw new IllegalArgumentException("Illegal amount.");
        }

        if(parts.containsKey(part) && (parts.getOrDefault(part, -1) > amount)) {
            parts.replace(part, parts.get(part) - amount);
            notifyPartCountChanged(part);
            return true;
        }
        return false;
    }

    public void addObserver(StockObserver observer) {
        if(observer == null) {
            throw new NullPointerException("Observer must not be null.");
        }
        observers.add(observer);
    }

    private void notifyPartCountChanged(Part part) {
        if(part == null){
            throw new NullPointerException("Part must not be null.");
        }

        for(StockObserver obs : observers) {
            obs.onPartCountChanged(part, getCount(part));
        }
    }
}
