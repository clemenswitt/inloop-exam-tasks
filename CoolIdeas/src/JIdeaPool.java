import com.sun.security.auth.UnixNumericUserPrincipal;

import java.util.*;

public class JIdeaPool {
    private Map<JTopic, Set<JIdea>> pool;

    public JIdeaPool() {
        this.pool = new HashMap<>();
    }

    public void add(JTopic topic) {
        if(topic == null) {
            throw new NullPointerException();
        }

        if(!pool.containsKey(topic)) {
            pool.put(topic, new LinkedHashSet<>());
        }
    }

    public void add(JIdea idea, JTopic topic) {
        if(topic == null || idea == null) {
            throw new NullPointerException();
        }

        // Topic bereits vorhanden?
        boolean ideaAlreadyExists = false;
        if(pool.containsKey(topic)) {
            // Ist idea bereits in topic vorhanden?
            for(JIdea i : pool.get(topic)) {
                if(i.getTitle().equals(idea.getTitle())) {
                    ideaAlreadyExists = true;
                    break;
                }
            }
            if(!ideaAlreadyExists) {
                pool.get(topic).add(idea);
            }
        } else {
            // idea bereits in anderem idea-Set vorhanden?
            for(Set<JIdea> ideaSet : pool.values()) {
                for(JIdea i : ideaSet) {
                    if(i.getTitle().equals(idea.getTitle()) && i != idea) {
                        ideaAlreadyExists = true;
                        break;
                    }
                }
            }
            if(!ideaAlreadyExists) {
                Set<JIdea> newIdeaSet = new HashSet<>();
                newIdeaSet.add(idea);
                pool.put(topic, newIdeaSet);
            }
        }
    }

    public boolean remove(JTopic topic) {
        if(topic == null) {
            throw new NullPointerException();
        }

        if(pool.containsKey(topic)) {
            pool.remove(topic);
            return true;
        }
        return false;
    }

    public boolean remove(JIdea idea) {
        if(idea == null) {
            throw new NullPointerException();
        }

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
        if(title == null) {
            throw new NullPointerException();
        }
        if(title.isEmpty()) {
            throw new IllegalArgumentException();
        }

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