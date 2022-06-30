public class JTopic extends JContent {
    private int id;

    public JTopic(String title, String description, int id) {
        super(title, description);

        if(id < 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Topic: " + super.getTitle() + "\n" + super.getDescription();
    }
}
