import java.util.*;

public class MyMatrix<T> implements Matrix<T> {
    private Map<MatrixIndex, T> myMatrix = new HashMap<>();
    private int RowCount = 0;
    private int ColumnCount = 0;

    @Override
    public int getRowCount() {
        return RowCount;
    }

    @Override
    public int getColumnCount() {
        return ColumnCount;
    }

    @Override
    public int getObjectCount() {
        return myMatrix.size();
    }

    @Override
    public int getDistinctObjectCount() {
        return new HashSet<T>(myMatrix.values()).size();
    }

    @Override
    public Iterator<T> iterator() {
        return new DepthFirstIterator();
    }

    @Override
    public T get(int row, int column) {
        if(row < 0 || column < 0 || row > RowCount - 1 || column > ColumnCount - 1) {
            throw new IllegalArgumentException();
        }

        for(MatrixIndex index: myMatrix.keySet()) {
            if(index.getRow() == row && index.getColumn() == column) {
                return myMatrix.get(index);
            }
        }
        return null;
    }

    @Override
    public T put(int row, int column, T value) {
        if(row < 0 || column < 0) {
            throw new IllegalArgumentException();
        }
        if(value == null) {
            return null;
        }

        // Bestehendes Element an angegebener Stelle suchen
        T foundValue = null;
        for(MatrixIndex index: myMatrix.keySet()) {
            if(index.getRow() == row && index.getColumn() == column) {
                foundValue = myMatrix.get(index);
            }
        }

        // Neues Element in Matrix ablegen
        MatrixIndex i = new MatrixIndex(row, column);
        myMatrix.put(i, value);

        // Matrixgröße aktualisieren
        updateBounds();

        return foundValue;
    }

    @Override
    public boolean contains(T value) {
        return myMatrix.containsValue(value);
    }

    public void updateBounds() {
        int maxRowIndex = 0;
        int maxColumnIndex = 0;
        for(MatrixIndex index: myMatrix.keySet()) {
            if(index.getRow() > maxRowIndex) maxRowIndex = index.getRow();
            if(index.getColumn() > maxColumnIndex) maxColumnIndex = index.getColumn();
        }
        RowCount = maxRowIndex + 1;
        ColumnCount = maxColumnIndex + 1;
    }

    public class DepthFirstIterator implements Iterator<T> {
        int currentRow = 0;
        int currentColumn = 0;

        @Override
        public boolean hasNext() {
            // Spalten vorhanden
            if(currentColumn < ColumnCount - 1) return true;

            // Keine Spalten mehr vorhanden; aktuelle Zeile ist letzte Zeile
            if(currentRow == RowCount - 1) return false;

            // Letzte Spalte -> noch gültige Werte vorhanden?
            for(int i = currentRow + 1; i < RowCount; i++) {
                if(get(i, currentColumn) != null) return true;
            }

            return false;
        }

        @Override
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();

            for(int i = currentColumn; i < ColumnCount; i++) {
                int row = 0;
                // wenn in aktueller Spalte -> mit nächster Zeile beginnen
                if(i == currentColumn) row = currentRow + 1;

                for(int j = row; j < RowCount; j++) {
                    if(get(j,i) != null) {
                        currentColumn = i;
                        currentRow = j;
                        return get(j,i);
                    }
                }
            }

            // Falls kein Element gefunden
            throw new NoSuchElementException();
        }
    }
}