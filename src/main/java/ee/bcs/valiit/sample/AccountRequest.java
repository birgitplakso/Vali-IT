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
        if(!isBlocked){
            return accountNumber;
        }
        return null;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        if(!isBlocked){
            return amount;
        }
        return -1;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isBlocked() {
        return isBlocked;
    }


    public void blockAccount(){
        isBlocked=true;
    }
}
