package ee.bcs.valiit.controller;

import ee.bcs.valiit.sample.AccountRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class BankController {

    private static HashMap<String, AccountRequest> accounts = new HashMap<>();

    @PostMapping("/createBankAccount")
    public static String createAccount(@RequestBody AccountRequest accountRequest) {
        accounts.put(accountRequest.getAccountNumber(), accountRequest);
        return "Account " + accountRequest.getAccountNumber() + " is created";

    }


    @PutMapping("/blockAccount")
    public String blockAccount(@RequestBody AccountRequest accountRequest){
        accounts.get(accountRequest.getAccountNumber()).blockAccount();

        return accountRequest.getAccountNumber()+" is blocked.";
    }

    @GetMapping("getAccountBalance/{accountNr}")
    public static String getBalance(@PathVariable("accountNr") String accountNr) {
        AccountRequest accountRequest=accounts.get(accountNr);
        double balance=accountRequest.getAmount();
        if(accountRequest.isBlocked()){
            return "Account is blocked!";
        }
        return "Your balance is " +balance;
    }

    @PutMapping("/deposit")
    public static String depositMoney(@RequestBody AccountRequest accountRequest) {
        if (accountRequest.getAmount() > 0) {
            AccountRequest customerData = accounts.get(accountRequest.getAccountNumber());
            double balance=customerData.getAmount();
            if(customerData.isBlocked()){
                return "Account is blocked!";
            }else{
                balance = balance + accountRequest.getAmount();
                customerData.setAmount(balance);
                accounts.replace(accountRequest.getAccountNumber(), customerData);
                return " "+accountRequest.getAmount() + " added to account " + accountRequest.getAccountNumber()
                        + "\n New balance is: " + balance;
            }
        } else {
            return "Invalid amount";
        }
    }

    @PutMapping("/withdraw")
    public static String withdrawMoney(@RequestBody AccountRequest accountRequest){
        if (accountRequest.getAmount() > 0) {
            AccountRequest customerData = accounts.get(accountRequest.getAccountNumber());
            double balance=customerData.getAmount();
            if(customerData.isBlocked()){
                return "Account is blocked!";
            }else if(balance >= accountRequest.getAmount()) {
                balance = balance - accountRequest.getAmount();
                customerData.setAmount(balance);
                accounts.replace(accountRequest.getAccountNumber(), customerData);
                return " "+accountRequest.getAmount()+ " is withdrawn, new balance is: " + balance;
            } else {
                return "Not enough money on account";
            }
        } else {
            return "Invalid amount";
        }
    }

    @PutMapping("transferMoney/{firstAccountNr}")
    public static String transfer(@PathVariable("firstAccountNr") String firstAccountNr,
                                  @RequestBody AccountRequest accountRequest) {
        AccountRequest firstCustomerData = accounts.get(firstAccountNr);
        double firstAccountBalance = firstCustomerData.getAmount();
        if(firstCustomerData.isBlocked()){
            return "The account you want to transfer from, is blocked!";
        }
        else if (firstAccountBalance >= accountRequest.getAmount()) {
            firstAccountBalance = firstAccountBalance - accountRequest.getAmount();
            firstCustomerData.setAmount(firstAccountBalance);
            accounts.replace(firstAccountNr, firstCustomerData);

            AccountRequest secondCustomerData = accounts.get(accountRequest.getAccountNumber());
            double secondAccountBalance = secondCustomerData.getAmount();

            if(secondCustomerData.isBlocked()){ //account value -1 means blocked account
                return "The account you want to transfer to, is blocked!";
            }
            secondAccountBalance = secondAccountBalance + accountRequest.getAmount();
            secondCustomerData.setAmount(secondAccountBalance);
            accounts.replace(accountRequest.getAccountNumber(), secondCustomerData);
            return accountRequest.getAmount() + " is transferred from " + firstAccountNr +
                    " to " + accountRequest.getAccountNumber();
        } else {
            return "Not enough money on account nr: " + firstAccountNr;
        }

    }
}
