public class BankAccount {
    private double balance;
    private double lineOfCredit;
    private String accountNumber;
    private AccountState state;

    public BankAccount(String accountNumber, double lineOfCredit) {
        if(accountNumber == null) throw new NullPointerException();
        if(lineOfCredit < 0 || accountNumber.isEmpty()) throw new IllegalArgumentException();
        this.accountNumber = accountNumber;
        this.lineOfCredit = lineOfCredit;
        this.balance = 0;
        this.state = new Positive();
    }

    public boolean payIn(double amount) {
        return state.payIn(amount);
    }

    public boolean payOff(double amount) {
        return state.payOff(amount);
    }

    public boolean close() {
        if(balance == 0 && !(state instanceof Closed)) {
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
            if(amount <= 0) throw new IllegalArgumentException();
            balance += amount;
            return true;
        }

        public boolean payOff(double amount) {
            if(amount <= 0) throw new IllegalArgumentException();
            balance -= amount;
            return true;
        }

        public String toString() {
            return this.getClass().getSimpleName();
        }

        public abstract void payInterest();

        public abstract void printBalance();
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
    }

    class Positive extends AccountState {
        public boolean payIn(double amount) {
            return super.payIn(amount);
        }

        public boolean payOff(double amount) {
            if(balance - amount == -lineOfCredit) state = new Frozen();
            else if(balance - amount < -lineOfCredit) return false;
            else if(amount > balance) state = new Negative();
            return super.payOff(amount);
        }

        public void payInterest() {
            balance *= 1.01;
        }

        public void printBalance() {
            System.out.println("Balance is POSITIVE: +" + getBalance() + ".");
        }
    }

    class Negative extends AccountState {
        public boolean payIn(double amount) {
            if(balance + amount >= 0) state = new Positive();
            return super.payIn(amount);
        }

        public boolean payOff(double amount) {
            if(balance - amount == -lineOfCredit) state = new Frozen();
            else if(balance - amount < -lineOfCredit) return false;
            return super.payOff(amount);
        }

        public void payInterest() {
            balance *= 1.03;
            if(balance <= -lineOfCredit) state = new Frozen();
        }

        public void printBalance() {
            System.out.println("Balance is NEGATIVE: " + getBalance() + ".");
        }
    }

    class Frozen extends AccountState {
        public boolean payIn(double amount) {
            if(balance + amount >= 0) state = new Positive();
            else if(balance + amount > -lineOfCredit) state = new Negative();
            return super.payIn(amount);
        }

        public boolean payOff(double amount) {
            return false;
        }

        public void payInterest() {
            balance *= 1.05;
        }

        public void printBalance() {
            System.out.println("Balance is NEGATIVE: " + getBalance() + ". You need to pay in money.");
        }
    }
}