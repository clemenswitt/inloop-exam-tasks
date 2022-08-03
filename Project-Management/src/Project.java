import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {
    private String name;
    private String description;
    private Task mainTask;

    public Project(String name, String description, double rate) {
        if(name == null || description == null) {
            throw new NullPointerException();
        }
        if(rate < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.description = description;
        this.mainTask = new Task(name, description, rate);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setTask(Task newTask) {
        if(newTask == null) {
            throw new NullPointerException();
        }
        this.mainTask = newTask;
    }

    public double getDuration() {
        return mainTask.getTimeRequired();
    }

    public long getTotalCost() {
        return mainTask.getCostEstimate();
    }

    public Map<LocalDate, List<Deliverable>> allDeliverables() {
        List<Deliverable> allDeliverableslist = mainTask.allDeliverables();
        Map<LocalDate, List<Deliverable>> allDeliverablesMap = new HashMap<>();

        for(Deliverable item : allDeliverableslist) {
            if(allDeliverablesMap.containsKey(item.getDate())) {
                List<Deliverable> currentDateDeliverables = allDeliverablesMap.get(item.getDate());
                currentDateDeliverables.add(item);
                allDeliverablesMap.replace(item.getDate(), currentDateDeliverables);
            }
            else {
                List<Deliverable> currentDateDeliverables = new ArrayList<>();
                currentDateDeliverables.add(item);
                allDeliverablesMap.put(item.getDate(), currentDateDeliverables);
            }
        }

        return allDeliverablesMap;
    }
}