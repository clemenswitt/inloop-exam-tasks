import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class PredicateIterator<T> implements Iterator<T> {
    private Predicate<T> predicate;
    private Iterator<T> iter;
    private Queue<T> queue = new LinkedList<>();

    public PredicateIterator(Iterator<T> iter, Predicate<T> predicate) {
        if(iter == null || predicate == null) {
            throw new NullPointerException();
        }
        this.iter = iter;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        while(iter.hasNext()) {
            // Prüfen, ob Queue nicht leer -> true; wenn Queue leer -> solange iter.next() bis zurückgegebenes Element Prädikatsbedingung erfüllt
            if(!queue.isEmpty()) {
                return true;
            }

            T nextEl = iter.next();
            if(predicate.test(nextEl)) {
                queue.add(nextEl);
                return true;
            }
        }
        return false;
    }

    public T next() {
        // Prüfen, ob nächstes Element existiert mit hasNext() --> queue wird ggf. mit nächstem Element gefüllt
        hasNext();
        if(!queue.isEmpty()) {
            return queue.poll();
        }
        throw new NoSuchElementException();
    }
}