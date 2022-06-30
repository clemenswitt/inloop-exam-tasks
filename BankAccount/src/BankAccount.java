public class BankAccount {
    private double balance = 0.0;
    private double lineOfCredit;
    private String accountNumber;
    private AccountState state;

    public BankAccount(String accountNumber, double lineOfCredit) {
        if(accountNumber == null) throw new NullPointerException();
        if(accountNumber.isEmpty()) throw new IllegalArgumentException();

        if(lineOfCredit < 0) throw new IllegalArgumentException();
        this.accountNumber = accountNumber;
        this.lineOfCredit = lineOfCredit;
        this.state = new Positive();
    }

    public boolean payIn(double amount) {
        if(amount <= 0) throw new IllegalArgumentException();
        return state.payIn(amount);
    }

    public boolean payOff(double amount) {
        if(amount <= 0) throw new IllegalArgumentException();
        return state.payOff(amount);
    }

    public boolean close() {
        if(balance == 0.0) {
            state = new Closed();
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public String getState() {
        return state.toString();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printBalance() {
        state.printBalance();
    }

    public void payInterest() {
        state.payInterest();
    }



    abstract class AccountState {
        public boolean payIn(double amount) {
            if(state instanceof Closed) return false;

            balance += amount;
            return true;
        }

        public boolean payOff(double amount) {
            if((balance + lineOfCredit) < amount || state instanceof Closed) return false;

            balance -= amount;
            return true;
        }

        public String toString() {
            return "Account State";
        }

        public void payInterest() {
            throw new IllegalStateException();
        }

        public abstract void printBalance();
    }

    class Positive extends AccountState {
        public boolean payIn(double amount) {
            return super.payIn(amount);
        }

        public boolean payOff(double amount) {
            if(!super.payOff(amount)) return false;

            if(balance <= (-lineOfCredit)) state = new Frozen();
            else if(balance < 0) state = new Negative();

            return true;
        }

        public void payInterest() {
            balance *= 1.01;
        }

        public void printBalance() {
            System.out.println("Balance is POSITIVE: +" + getBalance() + ".");
        }

        @Override
        public String toString() {
            return "Positive";
        }
    }

    class Negative extends AccountState {
        public boolean payIn(double amount) {
            super.payIn(amount);
            if(balance >= 0) state = new Positive();
            return true;
        }

        public boolean payOff(double amount) {
            if(!super.payOff(amount)) return false;
            if(balance <= (-lineOfCredit)) state = new Frozen();
            return true;
        }

        public void payInterest() {
            // Balance hat negativen Wert -> wird reduziert
            balance *= 1.03;
            if(balance <= (-lineOfCredit)) state = new Frozen();
        }

        public void printBalance() {
            System.out.println("Balance is NEGATIVE: " + getBalance() + ".");
        }

        @Override
        public String toString() {
            return "Negative";
        }
    }

    class Frozen extends AccountState {
        public boolean payIn(double amount) {
            super.payIn(amount);
            if(balance > (-lineOfCredit)) state = new Negative();
            if(balance >= 0) state = new Positive();
            return true;
        }

        public boolean payOff(double amount) {
            return false;
        }

        public void payInterest() {
            // Balance hat negativen Wert -> wird reduziert
            balance *= 1.05;
        }

        public void printBalance() {
            System.out.println("Balance is NEGATIVE: " + getBalance() + ". You need to pay in money.");
        }

        @Override
        public String toString() {
            return "Frozen";
        }
    }

    class Closed extends AccountState {
        public boolean payIn(double amount) {
           return false;
        }

        public boolean payOff(double amount) {
            return false;
        }

        public void payInterest() {
            throw new IllegalStateException();
        }

        public void printBalance() {
            System.out.println("This account is CLOSED. The balance is 0.");
        }

        @Override
        public String toString() {
            return "Closed";
        }
    }
}
