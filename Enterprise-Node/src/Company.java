public class Company extends AbstractUnit {
    public Company(String name) {
        super(name);
    }

    @Override
    public boolean add(AbstractEnterpriseUnit childNode) {
        if(childNode == null) {
            throw new NullPointerException();
        }
        if(childNode instanceof Team || childNode instanceof Holding || childNode instanceof Company) {
            throw new IllegalArgumentException();
        }
        return super.add(childNode);
    }
}
