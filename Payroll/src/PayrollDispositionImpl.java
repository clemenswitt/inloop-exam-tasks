import java.util.HashMap;
import java.util.Map;

public class PayrollDispositionImpl implements PayrollDisposition {
    private Map<Employee, Double> payments;

    public PayrollDispositionImpl() {
        payments = new HashMap<>();
    }

    @Override
    public void sendPayment(Employee empl, double payment) {
        if(empl == null) {
            throw new NullPointerException("Employee must not be null.");
        }
        if(payment <= 0) {
            throw new IllegalArgumentException("Invalid payment.");
        }

        payments.put(empl, payment);
    }

    public double getTotal() {
        double totalSum = 0;
        for(Employee em : payments.keySet()) {
            totalSum += payments.get(em);
        }
        return totalSum;
    }

    public double getAverage() {
        if(payments.isEmpty()) {
            return 0;
        }
        return getTotal() / payments.size();
    }

    public Map<Employee, Double> getPayments() {
        return payments;
    }
}
