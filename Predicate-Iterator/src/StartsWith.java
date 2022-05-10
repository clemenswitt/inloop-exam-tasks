public class StartsWith implements Predicate<String> {
    private String prefix;

    public StartsWith(String prefix) {
        if(prefix == null) {
            throw new IllegalArgumentException("Prefix must not be null.");
        }
        this.prefix = prefix;
    }

    @Override
    public boolean test(String value) {
        if(value == null || value.length() < prefix.length()) {
            return false;
        }

        String valueSubString = value.substring(0,prefix.length());
        return valueSubString.equals(prefix);
    }
}
