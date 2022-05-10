import java.util.List;

public class Adapter extends Project implements IProject {
    private Task mainTask;

    public Adapter(String name, String description, double rate) {
        super(name, description, rate);

        if(name.isEmpty() || description.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
    }

    @Override
    public void setTask(Task task) {
        if(task == null) {
            throw new NullPointerException("Task must not be null.");
        }
        this.mainTask = task;
    }

    @Override
    public double getDuration() {
        return mainTask.getTimeRequired();
    }

    @Override
    public long getTotalCost() {
        return mainTask.getCostEstimate();
    }

    @Override
    public List<Deliverable> getDeliverables() {
        return mainTask.allDeliverables();
    }
}
