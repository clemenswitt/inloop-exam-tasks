public class Components extends Part {
    public Components(String id, String name) {
        super(id, name);

        if(id.isEmpty() || name.isEmpty()) {
            throw new IllegalArgumentException("ID & Name must not be empty");
        }
    }
}
