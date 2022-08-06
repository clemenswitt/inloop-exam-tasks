import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class JMember implements ContentObserver {
    private Set<JTopic> topics = new HashSet<>();

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

    public void update(JContent content) {
        if(content == null) throw new NullPointerException();
        System.out.println("The topic "+ ((JTopic) content).getId() + " has been updated!");
    }
}