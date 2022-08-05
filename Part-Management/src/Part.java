public abstract class Part {
    private String id;
    private String name;

    public Part(String id, String name) {
        if(name == null || id == null) {
            throw new NullPointerException();
        }
        if(name.isEmpty() || id.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}