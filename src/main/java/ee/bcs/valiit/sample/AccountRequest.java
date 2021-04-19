package ee.bcs.valiit.sample;

public class AccountRequest {
    private String ownerName;
    private String accountNumber;
    private double amount;
    private boolean isBlocked=false;

    public AccountRequest() {
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAccountNumber() {
            return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
            return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void blockAccount(){
        isBlocked=true;
    }
}
