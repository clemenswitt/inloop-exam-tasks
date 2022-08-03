public class Payroll {
    private int payday;
    private PayrollDisposition disposition;

    public Payroll(PayrollDisposition disposition, int payday) {
        if(disposition == null) throw new NullPointerException();
        if(payday < 1 || payday > 30) throw new IllegalArgumentException();
        this.payday = payday;
        this.disposition = disposition;
    }

    public void doPayroll(PayrollDB db) {
        if(db == null) throw new NullPointerException();
        for(Employee employee : db.getEmployeeList()) {
            if(employee.isPayday(payday) && employee instanceof Appointee) {
                try {
                    disposition.sendPayment(employee, employee.calculatePay() - employee.calculateDeductions());
                } catch(UnpayableEmployeeException e) {}
            }
        }
    }
}