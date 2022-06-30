import javax.management.MBeanServerInvocationHandler;
import java.util.LinkedList;
import java.util.Queue;

public class VehicleQueue implements ClockObserver {
    private double entryDelay;
    private double exitDelay;
    private int trafficLightRate;
    private boolean greenLight = false;
    private VehicleGenerator generator;
    private Queue<Vehicle> queue;
    private int currentTime;

    public VehicleQueue(double entryDelay, double exitDelay, int trafficLightRate, VehicleGenerator generator) {

        if(entryDelay <= 0 || exitDelay <= 0 || trafficLightRate <= 0) {
            throw new IllegalArgumentException();
        }
        if(generator == null) {
            throw new NullPointerException();
        }

        this.entryDelay = entryDelay;
        this.exitDelay = exitDelay;
        this.trafficLightRate = trafficLightRate;
        this.generator = generator;
        this.queue = new LinkedList<>();
    }

    public void enter() {
        queue.add(generator.createVehicle());
    }

    public void leave() {
        queue.poll();
    }

    public double getLength() {
        double length = 0.0;
        for(Vehicle v : queue) {
            length += v.getLength();
        }
        return length;
    }

    public int getSize() {
        return queue.size();
    }

    // Ampelfarbe umschalten
    public void switchLight() {
        if(currentTime != 0 && remainingTimeToSwitchToGreenLight(currentTime) % trafficLightRate == 0) {
            greenLight = !greenLight;
        }
    }

    public int remainingTimeToSwitchToGreenLight(int time) {
        return time % (2 * trafficLightRate);
    }

    // Ampel grün, wenn in erster Hälfte der Ampelphase (2x trafficLightRate)
    public boolean isGreenLight(int time) {
        return remainingTimeToSwitchToGreenLight(time) >= trafficLightRate;
    }

    // Ampel im Vgl. zu letztem Tick umgeschalten?
    public boolean isSwitchedToRedFromLastTime(int time) {
        return time != 0 && !isGreenLight(time) && isGreenLight(time -1);
    }

    // Aktuell mögl. Menge eingefahrener Fzg.
    public double vehiclesEntered(double time) {
        return Math.floor(time / entryDelay);
    }

    // Aktuell mögl. Menge ausgefahrener Fzg.
    public double vehiclesLeft(double time) {
        if(time < trafficLightRate) {
            return 0d;
        }
        return Math.floor((time % trafficLightRate) / exitDelay);
    }

    // An aktuellem Tick eingefahrene Fzg.
    public double vehiclesToEnterAtCurrentTick() {
        return vehiclesEntered(currentTime) - vehiclesEntered(currentTime - 1);
    }

    public double getLastLeave() {
        return Math.round(exitDelay * vehiclesLeft(currentTime - 1));
    }

    // An aktuellem Tick ausgefahrene Fzg.
    public double vehiclesLeftAtCurrentTick() {
        if(isSwitchedToRedFromLastTime(currentTime)) {
            return Math.floor((trafficLightRate - getLastLeave()) / exitDelay);
        }
        return vehiclesLeft(remainingTimeToSwitchToGreenLight(currentTime)) - vehiclesLeft(remainingTimeToSwitchToGreenLight(currentTime - 1));
    }



    public void tick(int currentTime) {
        this.currentTime = currentTime;

        switchLight();

        double toEnter = vehiclesToEnterAtCurrentTick();
        for (int i = 0; i < toEnter; i++) this.enter();

        double toLeave = vehiclesLeftAtCurrentTick();
        for (int i = 0; i < toLeave; i++) this.leave();
    }
}
