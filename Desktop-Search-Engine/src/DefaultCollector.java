import java.util.HashSet;
import java.util.Set;

public class DefaultCollector implements KeywordCollector {
    public Set<String> getKeywords(Resource res) {
       if(res == null) {
           throw new NullPointerException();
       }
       return new HashSet<>(){{add(res.getName());}};
    }
}