public class Volunteer extends Employee {
    public Volunteer(String id) {
        super(id);
        if(id == null) {
            throw new NullPointerException("ID must not be null.");
        }
    }

    @Override
    public boolean isPayday(int dayOfMonth) {
        if(dayOfMonth < 1 || dayOfMonth > 30) {
            throw new IllegalArgumentException("Illegal date.");
        }
        return false;
    }

    @Override
    public double calculatePay() throws UnpayableEmployeeException {
        throw new UnpayableEmployeeException("Volunteers are unpayed.");
    }

    @Override
    public double calculateDeductions() {
        return 0;
    }
}
