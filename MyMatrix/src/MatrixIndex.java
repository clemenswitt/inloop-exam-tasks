import java.util.Random;

public class MatrixIndex {
    private int row;
    private int column;

    public MatrixIndex(int row, int column) {
        if(row < 0 || column < 0) throw new IllegalArgumentException();
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int hashCode() {
       String hashCode = row + "000" + column;
       return Integer.parseInt(hashCode);
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
}