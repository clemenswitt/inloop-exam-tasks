import java.util.HashMap;
import java.util.Map;

public class PayrollDispositionImpl implements PayrollDisposition {
    private Map<Employee, Double> payments;

    public PayrollDispositionImpl() {
        payments = new HashMap<>();
    }

    public double getTotal() {
        double total = 0;
        for(Employee employee : payments.keySet()) {
            total += payments.get(employee);
        }
        return total;
    }

    public double getAverage() {
        if(payments.isEmpty()) return 0;
        else return getTotal() / payments.size();
    }

    public Map<Employee, Double> getPayments() {
        return payments;
    }

    @Override
    public void sendPayment(Employee empl, double payment) {
        if(empl == null) throw new NullPointerException();
        if(payment <= 0) throw new IllegalArgumentException();
        payments.put(empl, payment);
    }
}