import java.util.HashSet;
import java.util.Set;

public class PlainTextCollector implements KeywordCollector {
    @Override
    public Set<String> getKeywords(Resource res) {
        if(res == null) {
            throw new NullPointerException();
        }

        TextFileIterator it = new TextFileIterator(res);
        Set<String> keywords = new HashSet<>();

        while(it.hasNext()) {
            keywords.add(it.next());
        }

        return keywords;
    }
}