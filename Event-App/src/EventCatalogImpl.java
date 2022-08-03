import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class EventCatalogImpl extends TreeMap<Event, Set<Time>> implements EventCatalog {
    private Map<Event, Set<Time>> catalog = new TreeMap<>();

    @Override
    public boolean addCatalogEntry(Event e, Set<Time> tSet) {
        if(e == null || tSet == null) throw new NullPointerException();
        if(tSet.contains(null)) throw new NullPointerException();

        if(!catalog.containsKey(e)) {
            catalog.put(e, tSet);
            return true;
        }
        return false;
    }

    @Override
    public boolean addTimeToEvent(Event e, Time t) {
        if(e == null || t == null) throw new NullPointerException();
        if(catalog.containsKey(e) && !catalog.get(e).contains(t)) {
            return catalog.get(e).add(t);
        }
        return false;
    }

    @Override
    public Set<Event> getAllEvents() {
        return catalog.keySet();
    }

    @Override
    public Set<Time> getTimesOfEvent(Event e) {
        if(e == null) throw new NullPointerException();
        return catalog.get(e);
    }

    @Override
    public Map<Event, Set<Time>> filterByEventCategory(EventCategory category) {
        if(category == null) throw new NullPointerException();
        Map<Event, Set<Time>> filteredMap = new TreeMap<>();
        for(Event e : catalog.keySet()) {
            if(e.getCategory() == category) filteredMap.put(e, catalog.get(e));
        }
        return filteredMap;
    }

    @Override
    public Set<Time> deleteEvent(Event e) {
        if(e == null) throw new NullPointerException();
        return catalog.remove(e);
    }

    @Override
    public boolean deleteTime(Event e, Time t) {
        if(e == null || t == null) throw new NullPointerException();
        if(catalog.get(e) == null || !catalog.get(e).contains(t)) return false;
        return catalog.get(e).remove(t);
    }
}