import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Stock {
    private Map<Part, Integer> parts = new HashMap<>();
    private List<StockObserver> observers = new ArrayList<>();

    public int getCount(Part part) {
        if(part == null) {
            throw new NullPointerException();
        }
        return parts.getOrDefault(part, -1);
    }

    public boolean insert(Part part, int amount) {
        if(part == null) {
            throw new NullPointerException();
        }
        if(amount <= 0) {
            throw new IllegalArgumentException();
        }
        if(parts.containsKey(part)) {
            parts.replace(part, parts.get(part) + amount);
        }
        else{
            parts.put(part, amount);
        }
        notifyPartCountChanged(part);
        return true;
    }

    public boolean remove(Part part, int amount) {
        if(part == null) {
            throw new NullPointerException();
        }
        if(amount <= 0) {
            throw new IllegalArgumentException();
        }
        if(parts.containsKey(part) && parts.getOrDefault(part, -1) > amount) {
            parts.replace(part, parts.get(part) - amount);
            notifyPartCountChanged(part);
            return true;
        }
        return false;
    }

    public void addObserver(StockObserver observer) {
        if(observer == null) {
            throw new NullPointerException();
        }
        observers.add(observer);
    }

    public void notifyPartCountChanged(Part part) {
        if(part == null) {
            throw new NullPointerException();
        }
        for(StockObserver o : observers) {
            o.onPartCountChanged(part, getCount(part));
        }
    }
}