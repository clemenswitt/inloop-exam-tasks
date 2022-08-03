import java.util.SortedSet;
import java.util.TreeSet;

public class Team extends AbstractEnterpriseUnit{
    private StaffMember teamLeader;
    public Team(String name, StaffMember teamLeader) {
        super(name);
        if(teamLeader == null) throw new NullPointerException();
        this.teamLeader = teamLeader;
    }

    public StaffMember getTeamLeader() {
        return teamLeader;
    }

    public SortedSet<StaffMember> getTeamMembers() {
        SortedSet<StaffMember> team = new TreeSet<>();
        team.add(teamLeader);
        StaffMemberIterator it = new StaffMemberIterator(teamLeader.getDirectSubordinates());
        while(it.hasNext()) team.add(it.next());
        return team;
    }
}