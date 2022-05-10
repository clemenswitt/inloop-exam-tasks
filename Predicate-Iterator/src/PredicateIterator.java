import java.util.Iterator;

public class PredicateIterator<T> implements Iterator<T> {
    private final Iterator<T> iter;
    private final Predicate<T> predicate;

    private T nextEl;
    private T lastHandledElement;

    public PredicateIterator(Iterator<T> iter, Predicate<T> predicate) {
        if(iter == null || predicate == null) {
            throw new NullPointerException("Iter & Predicate must not be null.");
        }
        this.iter = iter;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        while(iter.hasNext()) {
            // iter.next() muss hier bereits in iter.hasNext() aufgerufen werden, um Gültigkeit der Prädikatsbedingung prüfen zu können
            // -> wenn nicht zutreffend, muss betreffendes Element ignoriert werden
            // -> wenn passendes Element gefunden, Zwischenspeicherung mit nextEl nötig (erneuter Aufruf von iter.next() würde zum Überspringen des gefundenen Elements führen)
            T next = iter.next();
            if(predicate.test(next)) {
                nextEl = next;
                return true;
            }
        }
        return false;
    }

    @Override
    public T next() {
        // Hat sich nextEl seit dem letzten Aufruf von next() verändert? -> nein: auf nächsten Listenelement setzen
        if(lastHandledElement != null && lastHandledElement.equals(nextEl)){
            nextEl = iter.next();
        }

        // Nächstes Element auf Prädikatsbedingung prüfen -> weiter inkrementieren, wenn nicht zutreffend
        while(!predicate.test(nextEl)){
            nextEl = iter.next();
        }

        // Letztes behandeltes nextEl puffern
        lastHandledElement = nextEl;
        return nextEl;
    }
}
