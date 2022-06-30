import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class EventCatalogImpl extends TreeMap<Event, Set<Time>> implements EventCatalog {
    private Map<Event, Set<Time>> catalog;

    public EventCatalogImpl() {
        catalog = new TreeMap<>();
    }

    @Override
    public boolean addCatalogEntry(Event e, Set<Time> tSet) throws NullPointerException {
        if(e == null || tSet == null) {
            throw new NullPointerException();
        }
        for(Time t : tSet){
            if(t == null) {
                throw new NullPointerException();
            }
        }

        if(catalog.containsKey(e)) {
            return false;
        }
        catalog.put(e, tSet);
        return true;
    }

    @Override
    public boolean addTimeToEvent(Event e, Time t) {
        if(e == null || t == null) {
            throw new NullPointerException();
        }
        Set<Time> times = catalog.get(e);

        if(times == null || times.contains(t)) {
            return false;
        }
        times.add(t);
        catalog.put(e, times);
        return true;
    }

    @Override
    public Set<Event> getAllEvents() {
        return catalog.keySet();
    }

    @Override
    public Set<Time> getTimesOfEvent(Event e) {
        if(e == null) {
            throw new NullPointerException();
        }
        return catalog.get(e);
    }

    @Override
    public Map<Event, Set<Time>> filterByEventCategory(EventCategory category) {
        if(category == null) {
            throw new NullPointerException();
        }

        Map<Event, Set<Time>> filteredCatalog = new TreeMap<>();

        for(Event e : catalog.keySet()) {
            if(e.getCategory() == category) {
                filteredCatalog.put(e, catalog.get(e));
            }
        }

        return filteredCatalog;
    }

    @Override
    public Set<Time> deleteEvent(Event e) {
        if(e == null) {
            throw new NullPointerException();
        }
        return catalog.remove(e);
    }

    @Override
    public boolean deleteTime(Event e, Time t) {
        if(e == null || t == null) {
            throw new NullPointerException();
        }

        if(!catalog.containsKey(e)) {
            return false;
        }

        Set<Time> times = catalog.get(e);

        if(!times.contains(t)) {
            return false;
        }

        return times.remove(t);
    }
}
