public class Appointee extends Employee {
    private int payday;
    private int hoursPerMonth;
    private double payPerHour;

    public Appointee(String id, int payday, int hoursPerMonth, double payPerHour) {
        super(id);

        if(id.isEmpty()) {
            throw new NullPointerException("ID must not be null.");
        }
        if(payday < 1 || payday > 30 || hoursPerMonth <= 0 || payPerHour <= 0) {
            throw new IllegalArgumentException("Illegal argument values.");
        }

        this.payday = payday;
        this.hoursPerMonth = hoursPerMonth;
        this.payPerHour = payPerHour;
    }

    @Override
    public boolean isPayday(int dayOfMonth) {

        if(dayOfMonth < 1 || dayOfMonth > 30) {
            throw new IllegalArgumentException("Illegal date.");
        }

        if(dayOfMonth == payday) {
            return true;
        }
        return false;
    }

    @Override
    public double calculatePay() throws UnpayableEmployeeException {
        return hoursPerMonth * payPerHour;
    }

    @Override
    public double calculateDeductions() {
        try {
            return 0.4 * calculatePay();
        } catch(UnpayableEmployeeException e) {
            //
        }
        return 0;
    }
}
