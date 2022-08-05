import java.util.SortedSet;
import java.util.TreeSet;
import java.lang.Comparable;

public class StaffMember implements EnterpriseNode, Comparable<StaffMember> {
    private String name;
    private String job;
    private SortedSet<StaffMember> directSubordinates;

    public StaffMember(String name, String job) {
        if(name == null || job == null) throw new NullPointerException();
        if(name.isEmpty() || job.isEmpty()) throw new IllegalArgumentException();
        this.name = name;
        this.job = job;
        directSubordinates = new TreeSet<>();
    }

    public String getJob() {
        return job;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean addDirectSubordinate(StaffMember subordinate) {
        return directSubordinates.add(subordinate);
    }

    public boolean removeDirectSubordinate(StaffMember subordinate) {
        return directSubordinates.remove(subordinate);
    }

    public SortedSet<StaffMember> getDirectSubordinates() {
        return directSubordinates;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(StaffMember o) {
        if(o == null) throw new NullPointerException();
        return getName().compareTo(o.getName());
    }
}