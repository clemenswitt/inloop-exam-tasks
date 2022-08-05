import java.util.*;

public class Index {
    private Map<String, List<Resource>> index;

    public Index() {
        index = new HashMap<>();
    }

    public void add(Resource res) {
        if(res == null) {
            throw new NullPointerException();
        }

        //Get keywords of res
        Set<String> keyWordsOfRes = res.getType().getCollector().getKeywords(res);

        for(String keyWord : keyWordsOfRes) {
            //Put keyword if not already in index
            if(!index.containsKey(keyWord)) {
                index.put(keyWord, new ArrayList<>());
            }
            //Add resource to keyword if not already existent
            if(!index.get(keyWord).contains(res)) {
                index.get(keyWord).add(res);
            }
        }
    }

    public List<Resource> getResources(String keyword) {
        if(keyword == null) {
            throw new NullPointerException();
        }
        return index.getOrDefault(keyword, new ArrayList<>());
    }
}