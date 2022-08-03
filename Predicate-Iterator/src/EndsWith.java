public class EndsWith implements Predicate<String> {
    private String suffix;

    public EndsWith(String suffix) {
        if(suffix == null) {
            throw new IllegalArgumentException();
        }
        this.suffix = suffix;
    }

    public boolean test(String value) {
        if(value == null || value.length() < suffix.length() || !value.substring((value.length()-1) - (suffix.length() - 1), value.length()).equals(suffix)) {
            return false;
        }
        return true;
    }
}