import java.util.*;

public class Index {
    private Map<String, List<Resource>> index;

    public Index() {
        index = new HashMap<>();
    }

    public void add(Resource res) {
        if(res == null) {
            throw new NullPointerException("Ressource darf nicht null sein.");
        }

        Set<String> keywordsOfRes = res.getType().getCollector().getKeywords(res);

        for(String currentKeyword : keywordsOfRes) {

            // Keyword in index nicht enthalten -> neu anlegen
            if(!index.containsKey(currentKeyword)) {
                index.put(currentKeyword, new ArrayList<>());
            }

            // Keyword nun definitiv vorhanden
            // Hinzuzufügende Ressource noch nicht bei Keyword vorhanden? -> ArrayList des Keywords Ressource hinzufügen
            if(!index.get(currentKeyword).contains(res)) {
                index.get(currentKeyword).add(res);
            }
        }
    }

    public List<Resource> getResources(String keyword) {
        if(keyword.isEmpty()) {
            throw new IllegalArgumentException("Keyword darf nicht null sein.");
        }

        // Wenn keyword gefunden, wird entsprechende Liste zurückgegeben; sonst neue, leere ArrayList
        return index.getOrDefault(keyword, new ArrayList<>());
    }

}
