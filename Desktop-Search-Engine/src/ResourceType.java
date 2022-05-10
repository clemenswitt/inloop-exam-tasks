public class ResourceType {
    private String description;
    private KeywordCollector collector;

    public ResourceType(String desc, KeywordCollector collector) {
        if(desc == null || collector == null) {
            throw new NullPointerException("Argumente dürfen nicht null sein.");
        }
        if(desc.isEmpty()) {
            throw new IllegalArgumentException("Argumente dürfen nicht leer sein.");
        }


        this.description = desc;
        this.collector = collector;
    }

    public String getDescription() {
        return description;
    }

    public KeywordCollector getCollector() {
        return collector;
    }
}
