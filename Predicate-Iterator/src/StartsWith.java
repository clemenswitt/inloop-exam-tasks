public class StartsWith implements Predicate<String> {
    private String prefix;

    public StartsWith(String prefix) {
        if(prefix == null) {
            throw new IllegalArgumentException();
        }
        this.prefix = prefix;
    }

    public boolean test(String value) {
        if(value == null || value.length() < prefix.length() || !value.substring(0, prefix.length()).equals(prefix)) {
            return false;
        }
        return true;
    }
}