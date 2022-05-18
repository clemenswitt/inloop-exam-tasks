public class Holding extends AbstractUnit {
    public Holding(String name) {
        super(name);
    }

    public boolean add(AbstractEnterpriseUnit childNode) {
        if(childNode == null) {
            throw new NullPointerException();
        }
        if(childNode instanceof Team || childNode instanceof Holding || childNode instanceof Division) {
            throw new IllegalArgumentException();
        }
        return super.add(childNode);
    }
}
