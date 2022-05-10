import java.util.Set;
import java.util.TreeSet;

public class PlainTextCollector implements KeywordCollector {
    @Override
    public Set<String> getKeywords(Resource res) {
        if (res == null) {
            throw new NullPointerException("Resource darf nicht null sein");
        }

        TextFileIterator tfi = new TextFileIterator(res);

        Set<String> keywords = new TreeSet<>();

        while(tfi.hasNext()) {
            keywords.add(tfi.next());
        }

        return keywords;
    }
}
