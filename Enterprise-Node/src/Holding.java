public class Holding extends AbstractUnit {
    public Holding(String name) {
        super(name);
    }

    @Override
    public boolean add(AbstractEnterpriseUnit childNode) {
        if(childNode == null) throw new NullPointerException();
        if(!(childNode instanceof Company)) throw new IllegalArgumentException();
        return super.add(childNode);
    }
}