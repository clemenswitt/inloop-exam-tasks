import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class MatrixIndexTest {
    @Test
    public void setterMethodsShouldNotExist() {
        try {
            MatrixIndex.class.getMethod("setRow", int.class);
            fail("MatrixIndex.setRow() shouldn't exist!");
        } catch (NoSuchMethodException e) {
        }
        try {
            MatrixIndex.class.getMethod("setColumn", int.class);
            fail("MatrixIndex.setColumn() shouldn't exist!");
        } catch (NoSuchMethodException e) {
        }
    }

    @Test
    public void hashCodeAndEqualsShouldBeOverriden() {
        try {
            MatrixIndex.class.getMethod("equals", Object.class);
        } catch (NoSuchMethodException e) {
            fail("MatrixIndex.equals() should be overridden!");
        }
        try {
            MatrixIndex.class.getMethod("hashCode");
        } catch (NoSuchMethodException e) {
            fail("MatrixIndex.hashCode() should be overridden!");
        }
    }

    @Test
    public void equalIndexesShouldHaveSameHashCode() {
        MatrixIndex index1 = new MatrixIndex(2, 4);
        MatrixIndex index2 = new MatrixIndex(2, 4);
        String message = "MatrixIndex.hashCode() of two indexes should be equal if they have "
                + "the same row and column!";
        assertEquals(message, index1.hashCode(), index2.hashCode());
    }

    @Test
    public void indexesWithSameColumnAndRowShouldBeEqual() {
        MatrixIndex index1 = new MatrixIndex(5, 1);
        MatrixIndex index2 = new MatrixIndex(5, 1);

        String message1 = "Identical MatrixIndex objects should be equal according to equals(…)!";
        assertEquals(message1, index1, index1);
        assertEquals(message1, index2, index2);

        String message2 = "MatrixIndex objects with equal column and row should be equal according to equals(…)!";
        assertEquals(message2, index1, index2);
        assertEquals(message2, index2, index1);
    }

    @Test
    public void getterMethodsShouldReturnCorrectValues() {
        MatrixIndex matrixIndex = new MatrixIndex(3, 4);
        assertEquals("MatrixIndex.getRow() should return the right number!", matrixIndex.getRow(), 3);
        assertEquals("MatrixIndex.getColumn() should return the right number!", matrixIndex.getColumn(), 4);
    }
}
