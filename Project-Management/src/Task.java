import java.util.ArrayList;
import java.util.List;

public class Task extends ProjectItem {
    private List<ProjectItem> projectItems;

    public Task(String name, String details, double rate) {
        super(name, details, rate);
        if(name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        projectItems = new ArrayList<>();
    }

    public void addProjectItem(ProjectItem pi) {
        if(pi == null) {
            throw new NullPointerException();
        }
        projectItems.add(pi);
    }

    public void removeProjectItem(ProjectItem pi) {
        if(pi == null) {
            throw new NullPointerException();
        }
        projectItems.remove(pi);
    }

    public List<Deliverable> allDeliverables() {
        List<Deliverable> deliverables = new ArrayList<>();
        for(ProjectItem item : projectItems) {
            if(item instanceof Deliverable) {
                deliverables.add((Deliverable) item);
            }
            if(item instanceof Task) {
                deliverables.addAll(((Task) item).allDeliverables());
            }
        }
        return deliverables;
    }

    public double getTimeRequired() {
        double time = 0;
        for(ProjectItem item : projectItems) {
           time += item.getTimeRequired();
        }
        return time;
    }

    public long getMaterialCost() {
        long materialCost = 0;
        for(ProjectItem item : projectItems) {
            materialCost += item.getMaterialCost();
        }
        return materialCost;
    }
}