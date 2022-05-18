public class Person {
    private String name;

    public Person(String name) {
        if(name == null) {
            throw new NullPointerException("Name must not be null.");
        }
        if(name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
