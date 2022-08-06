import java.util.*;

public class MyMatrix<T> implements Matrix<T> {
    private Map<MatrixIndex, T> matrixEntries = new HashMap<>();

    @Override
    public int getColumnCount() {
        List<Integer> cols = new ArrayList<>();
        for(MatrixIndex i : matrixEntries.keySet()) {
            if(matrixEntries.get(i) != null) cols.add(i.getColumn());
        }
        if (cols.isEmpty()) return 0;
        return Collections.max(cols)+1;
    }

    @Override
    public int getRowCount() {
        List<Integer> rows = new ArrayList<>();
        for(MatrixIndex i : matrixEntries.keySet()) {
            if(matrixEntries.get(i) != null) rows.add(i.getRow());
        }

        if (rows.isEmpty()) return 0;
        return Collections.max(rows)+1;
    }

    @Override
    public int getObjectCount() {
        return matrixEntries.size();
    }

    @Override
    public int getDistinctObjectCount() {
        List<T> uniqueObjects = new ArrayList<>();
        for(T value : matrixEntries.values()) {
            if(!uniqueObjects.contains(value)) uniqueObjects.add(value);
        }
        return uniqueObjects.size();
    }

    @Override
    public T put(int row, int column, T value) {
        if(row < 0 || column < 0) throw new IllegalArgumentException();
        T oldValue = matrixEntries.get(new MatrixIndex(row, column));
        matrixEntries.put(new MatrixIndex(row, column), value);
        return oldValue;
    }

    @Override
    public T get(int row, int column) {
        if(row < 0 || column < 0 || row > getRowCount() - 1 || column > getColumnCount() - 1) throw new IllegalArgumentException();
        MatrixIndex index = new MatrixIndex(row, column);
        for(MatrixIndex i : matrixEntries.keySet()) {
            if(index.equals(i)) return matrixEntries.get(i);
        }
        return null;
    }

    @Override
    public boolean contains(T value) {
        return matrixEntries.containsValue(value);
    }

    @Override
    public Iterator<T> iterator() {
        return new DepthFirstIterator();
    }

    private class DepthFirstIterator implements Iterator<T> {
        private int currentRow = -1;
        private int currentCol = 0;

        @Override
        public boolean hasNext() {
            // Nächste Matrixposition ermitteln
            if(currentRow == getRowCount() - 1 && currentCol < getColumnCount() -1) {
                currentCol ++;
                currentRow = 0;
            } else if(currentRow < getRowCount() - 1) currentRow++;
            // Ende der Matrix erreicht
            else return false;

            // Nächstes Matrixelement ist null-Objekt -> übergehen
            if(get(currentRow, currentCol) == null) return hasNext();
            // Nächstes Matrixelement ist kein null-Objekt
            return true;
        }

        @Override
        public T next() {
            T next = matrixEntries.get(new MatrixIndex(currentRow, currentCol));
            // Wenn kein Element mehr vorhanden, bleibt hasNext() auf null-Objekt stehen.
            if(next == null) throw new NoSuchElementException();
            return next;
        }
    }
}