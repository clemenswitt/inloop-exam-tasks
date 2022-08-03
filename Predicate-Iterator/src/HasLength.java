public class HasLength implements Predicate<String> {
    private int length;

    public HasLength(int length) {
        if(length < 0) {
            throw new IllegalArgumentException();
        }
        this.length = length;
    }

    public boolean test(String value) {
        if(value == null || !(value.length() == length)) {
            return false;
        }
        return true;
    }
}