import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class PredicateIterator<T> implements Iterator<T> {
    private final Iterator<T> iter;
    private final Predicate<T> predicate;

    private T nextEl;
    private T lastHandledElement;

    private Queue<T> queue = new LinkedList<>();

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
            // Wenn Elemente in Queue vorhanden -> hasNext() bereits zuvor aufgerufen
            // Zunächst Elemente in Queue abarbeiten, bevor weitere iter.next() überprüft werden
            if(!queue.isEmpty()) {
                return true;
            }

            // iter.next() muss hier bereits in iter.hasNext() aufgerufen werden, um Gültigkeit der Prädikatsbedingung prüfen zu können
            // -> wenn nicht zutreffend, muss betreffendes Element ignoriert werden
            // -> wenn passendes Element gefunden, Zwischenspeicherung nötig (erneuter Aufruf von iter.next() würde zum Überspringen des gefundenen Elements führen)
            T next = iter.next();
            if(predicate.test(next)) {
                queue.add(next);
                return true;
            }
        }
        return false;
    }

    @Override
    public T next() {
        // Wenn Queue leer -> nächstes Element in iter suchen, welches Prädikatsbedingung erfüllt
        if(queue.isEmpty()) {
            while(iter.hasNext()) {
                T next = iter.next();
                if(predicate.test(next)) {
                    return next;
                }
            }
            throw new NoSuchElementException();
        }
        // Wenn Queue nicht leer -> oberstes Element zurückgeben
        return queue.poll();
    }
}
