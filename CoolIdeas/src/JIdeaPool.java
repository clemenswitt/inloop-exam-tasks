import java.util.*;

public class JIdeaPool {
    private Map<JTopic, Set<JIdea>> pool;

    public JIdeaPool() {
        this.pool = new HashMap<>();
    }

    public void add(JTopic topic) {
        if(topic == null) throw new NullPointerException();
        if(!pool.containsKey(topic)) pool.put(topic, new HashSet<>());
    }

    public void add(JIdea idea, JTopic topic) {
        if(idea == null || topic == null) throw new NullPointerException();
        boolean ideaExists = false;
        for(Set<JIdea> ideaSet : pool.values()) {
            for(JIdea i : ideaSet) {
                if(i.hashCode() == idea.hashCode()) ideaExists = true;
                break;
            }
        }
        if(!ideaExists) {
            if(!pool.containsKey(topic)) pool.put(topic, new HashSet<>(Arrays.asList(idea)));
            else {
                Set<JIdea> ideas = pool.get(topic);
                ideas.add(idea);
                pool.replace(topic, ideas);
            }
        }
    }

    public boolean remove(JTopic topic) {
        if(topic == null) throw new NullPointerException();
        if(pool.containsKey(topic)) {
            pool.remove(topic);
            return true;
        }
        return false;
    }

    public boolean remove(JIdea idea) {
        if(idea == null) throw new NullPointerException();

        boolean removed = false;
        for(Set<JIdea> ideaSet : pool.values()) {
            if(ideaSet.contains(idea)) {
                ideaSet.remove(idea);
                removed = true;
            }
        }
        return removed;
    }

    public JIdea getIdea(String title) {
        if(title == null) throw new NullPointerException();
        if(title.isEmpty()) throw new IllegalArgumentException();

        for(Set<JIdea> ideaSet : pool.values()) {
            for(JIdea idea : ideaSet) {
                if(idea.getTitle().equals(title)) {
                    return idea;
                }
            }
        }
        return null;
    }

    public int numberOfTopics() {
        return pool.size();
    }

    public int numberOfIdeas() {
        List<String> uniqueIdeas = new ArrayList<>();
        for(Set<JIdea> ideaSet : pool.values()) {
            for(JIdea idea : ideaSet) {
                if(!uniqueIdeas.contains(idea.getTitle())) uniqueIdeas.add(idea.getTitle());
            }
        }
        return uniqueIdeas.size();
    }

    public void removeDeclined() {
        for(Set<JIdea> ideaSet : pool.values()) {
            ideaSet.removeIf(JIdea::isDeclined);
        }
    }

    public void removeReleased() {
        for(Set<JIdea> ideaSet : pool.values()) {
            ideaSet.removeIf(JIdea::isReleased);
        }
    }
}