public class Payroll {
    private int payday;
    private PayrollDisposition disposition;

    public Payroll(PayrollDisposition disposition, int payday) {

        if(disposition == null) {
            throw new NullPointerException("Disposition must not be null.");
        }
        if(payday < 1 || payday > 30) {
            throw new IllegalArgumentException("Invalid Payday.");
        }

        this.payday = payday;
        this.disposition = disposition;
    }

    public void doPayroll(PayrollDB db) {
        if(db == null) {
            throw new NullPointerException("DB must not be null.");
        }

        for(Employee e : db.getEmployeeList()) {
           try {
               if(e instanceof Appointee && e.isPayday(payday)) {
                   disposition.sendPayment(e, e.calculatePay() - e.calculateDeductions());
               }
           } catch(UnpayableEmployeeException ex) {
               //
           }
        }

    }


}
