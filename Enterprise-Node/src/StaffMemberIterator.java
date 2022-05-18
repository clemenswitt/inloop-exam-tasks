import com.sun.source.tree.Tree;

import java.lang.Comparable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class StaffMemberIterator implements EnterpriseNodeIterator<StaffMember> {
    private Set<StaffMember> staffMemberSet;
    private Iterator<StaffMember> staffMemberIterator;

    public StaffMemberIterator(Set<StaffMember> directSubordinates) {
        if (directSubordinates == null) {
            throw new NullPointerException();
        }
        for(StaffMember member : directSubordinates) {
            if(member == null) {
                throw new NullPointerException();
            }
        }

        staffMemberSet = new TreeSet<>();
        for(StaffMember member : directSubordinates) {
            findSubordinatesRecursively(member);
        }
        staffMemberIterator = staffMemberSet.iterator();
    }

    private void findSubordinatesRecursively(StaffMember member) {
        if(member == null) {
            throw new NullPointerException();
        }

        staffMemberSet.add(member);
        if(!member.getDirectSubordinates().isEmpty()) {
            Set<StaffMember> subMembers = member.getDirectSubordinates();
            for(StaffMember subMember : subMembers) {
                findSubordinatesRecursively(subMember);
            }
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
