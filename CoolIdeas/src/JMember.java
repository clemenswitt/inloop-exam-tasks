import java.util.HashSet;
import java.util.Set;

public class JMember implements ContentObserver {
    private Set<JTopic> topics = new HashSet<>();

    public void update(JTopic content) {
        System.out.println("The topic " + content.getId() + " has been updated!");
    }

    public void subscribe(JTopic topic) {
        if(topic == null) throw new NullPointerException();
        topics.add(topic);
        topic.addObserver(this);
    }

    public void unsubscribe(JTopic topic) {
        if(topic == null) throw new NullPointerException();
        topics.remove(topic);
        topic.removeObserver(this);
    }

    public Set<JTopic> getSubscribedTopics() {
        return topics;
    }
}
