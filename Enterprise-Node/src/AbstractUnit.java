import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractUnit extends AbstractEnterpriseUnit {
    private Set<AbstractEnterpriseUnit> childNodes;

    public AbstractUnit(String name) {
        super(name);
        childNodes = new HashSet<>();
    }

    public boolean add(AbstractEnterpriseUnit childNode) {
        if(childNode == null) {
            throw new NullPointerException();
        }
        return childNodes.add(childNode);
    }

    public boolean remove(AbstractEnterpriseUnit childNode) {
        if(childNode == null) {
            throw new NullPointerException();
        }
        return childNodes.remove(childNode);
    }

    public Set<AbstractEnterpriseUnit> getChildNodes() {
        return childNodes;
    }
}