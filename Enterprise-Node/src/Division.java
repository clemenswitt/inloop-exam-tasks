public class Division extends AbstractUnit {
    public Division(String name) {
        super(name);
    }

    @Override
    public boolean add(AbstractEnterpriseUnit childNode) {
        if(childNode == null) {
            throw new NullPointerException();
        }
        if(childNode instanceof Company || childNode instanceof Holding || childNode instanceof Division) {
            throw new IllegalArgumentException();
        }
        return super.add(childNode);
    }
}
