import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends Project implements IProject {
    public Adapter(String name, String description, double rate) {
        super(name, description, rate);
    }

    @Override
    public void setTask(Task newTask) {
        super.setTask(newTask);
    }

    @Override
    public double getDuration() {
        return super.getDuration();
    }

    @Override
    public long getTotalCost() {
        return super.getTotalCost();
    }

    @Override
    public List<Deliverable> getDeliverables() {
        List<Deliverable> allDeliverables = new ArrayList<>();
        for(LocalDate date : super.allDeliverables().keySet()) {
            allDeliverables.addAll(super.allDeliverables().get(date));
        }
        return allDeliverables;
    }
}