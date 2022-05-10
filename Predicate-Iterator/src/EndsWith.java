import java.sql.Struct;

public class EndsWith implements Predicate<String> {
    private String suffix;

    public EndsWith(String suffix) {
        if(suffix == null) {
            throw new IllegalArgumentException("Suffix must not be null.");
        }
        this.suffix = suffix;
    }

    @Override
    public boolean test(String value) {
        if(value == null || value.length() < suffix.length()) {
            return false;
        }

        String suffixSubString = value.substring((value.length() - 1) - (suffix.length() - 1), value.length());
        return suffixSubString.equals(suffix);
    }
}
