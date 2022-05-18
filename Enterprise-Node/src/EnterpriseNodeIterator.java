import java.util.Iterator;

public interface EnterpriseNodeIterator<T> extends Iterator<T> {
    boolean hasNext();
    T next();
}
