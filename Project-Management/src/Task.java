import java.util.ArrayList;
import java.util.List;

public class Task extends ProjectItem {
    List<ProjectItem> projectItems;
    public Task(String name, String details, double rate) {
        super(name, details, rate);

        if(name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }

        projectItems = new ArrayList<>();
    }

    @Override
    public double getTimeRequired() {
        double requiredTime = 0;
        for(ProjectItem item : projectItems) {
            requiredTime += item.getTimeRequired();
        }
        return requiredTime;
    }

    @Override
    public long getMaterialCost() {
        long materialCost = 0;
        for(ProjectItem item : projectItems) {
            materialCost += item.getMaterialCost();
        }
        return materialCost;
    }

    public void addProjectItem(ProjectItem pi) {
        if(pi == null) {
            throw new NullPointerException("ProjectItem must not be null.");
        }
        projectItems.add(pi);
    }

    public void removeProjectItem(ProjectItem pi) {
        if(pi == null) {
            throw new NullPointerException("ProjectItem must not be null.");
        }
        projectItems.remove(pi);
    }

    public List<Deliverable> allDeliverables() {
        ArrayList<Deliverable> deliverables = new ArrayList<>();

        for(ProjectItem item : projectItems) {
            // Wenn item Deliverable -> direkt hinzufügen
            if(item instanceof Deliverable) {
                deliverables.add((Deliverable) item);
            }
            // Wenn item Task -> allDeliverables() auf item aufrufen -> zu Liste deliverables hinzufügen
            if(item instanceof Task) {
                deliverables.addAll(((Task) item).allDeliverables());
            }
        }

        return deliverables;
    }
}
