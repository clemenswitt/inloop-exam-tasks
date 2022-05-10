public abstract class Part {
    private String id;
    private String name;

    public Part(String id, String name) {
        if(id == null || name == null) {
            throw new NullPointerException("ID & Name must not be null.");
        }
        if(id.isEmpty() || name.isEmpty()) {
            throw new IllegalArgumentException("ID & Name must not be empty.");
        }
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
