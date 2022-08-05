import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class StaffMemberIterator implements EnterpriseNodeIterator<StaffMember> {
    private Set<StaffMember> allMembers;
    private Iterator<StaffMember> staffMemberIterator;

    public StaffMemberIterator(Set<StaffMember> directSubordinates) {
        if(directSubordinates == null) throw new NullPointerException();
        allMembers = new TreeSet<>();
        for(StaffMember member : directSubordinates) findSubordinatesRecursively(member);
        staffMemberIterator = allMembers.iterator();
    }

    public void findSubordinatesRecursively(StaffMember member) {
        if(member == null) throw new NullPointerException();
        allMembers.add(member);
        if(!member.getDirectSubordinates().isEmpty()) {
            for(StaffMember s : member.getDirectSubordinates()) findSubordinatesRecursively(s);
        }
    }

    @Override
    public boolean hasNext() {
        return staffMemberIterator.hasNext();
    }

    @Override
    public StaffMember next() {
        return staffMemberIterator.next();
    }
}