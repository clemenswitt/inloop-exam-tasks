import java.util.Set;
import java.util.TreeSet;

public class DefaultCollector implements KeywordCollector {

    @Override
    public Set<String> getKeywords(Resource res) {
        if (res == null) {
            throw new NullPointerException("Resource darf nicht null sein");
        }
        Set<String> keyword = new TreeSet<>();
        keyword.add(res.getName());
        return keyword;
    }
}
