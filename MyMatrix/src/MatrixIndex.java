import java.util.Objects;
import java.util.Random;

public class MatrixIndex {
    private int row;
    private int column;

    public MatrixIndex(int row, int column) {
        if(row < 0 || column < 0) {
            throw new IllegalArgumentException();
        }
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        return this.hashCode() == o.hashCode();
    }

    public int hashCode() {
        return Integer.parseInt(Integer.toString(row) + Integer.toString(column));
    }
}
