import java.util.ArrayList;
import java.util.List;

public class Clock {
    private int currentTime = 0;
    private int endOfTime;
    private List<ClockObserver> observers;


    public Clock(int endOfTime) {

        if(endOfTime <= 0) {
            throw new IllegalArgumentException();
        }

        this.endOfTime = endOfTime;
        observers = new ArrayList<>();
    }

    public void addObserver(ClockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ClockObserver observer) {
        observers.remove(observer);
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void run() {
        currentTime = 0;
        while(currentTime < endOfTime) {
            currentTime ++;
            tick(currentTime);
        }
    }

    public void tick(int currentTime) {
        for(ClockObserver obs : observers) {
            obs.tick(currentTime);
        }
    }

}
