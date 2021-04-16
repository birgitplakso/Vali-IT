package ee.bcs.valiit.controller;

import ee.bcs.valiit.sample.AccountRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class Controller4Ex {

    private static HashMap<String, Double> accountBalanceMap = new HashMap<>();


    @PostMapping("/createAccount")
    public static String createAccount(@RequestBody AccountRequest accountRequest) {
        accountBalanceMap.put(accountRequest.getAccountNumber(), accountRequest.getAmount());
        return "Account " + accountRequest.getAccountNumber() + " is created";

    }
//    @GetMapping("createAccount/{accountNr}")
//    public static String createAccount(@PathVariable("accountNr") String accountNr) {
//        accountBalanceMap.put(accountNr, 0.0);
//        return "Account "+accountNr+" is created";
//
//    }


    @GetMapping("getBalance/{accountNr}")
    public static String getBalance(@PathVariable("accountNr") String accountNr) {
        return "Your balance is " + accountBalanceMap.get(accountNr);
    }

    @PutMapping("/depositMoney")
    public static String depositMoney(@RequestBody AccountRequest accountRequest) {
        if (accountRequest.getAmount() > 0) {
            double balance = accountBalanceMap.get(accountRequest.getAccountNumber());
            balance = balance + accountRequest.getAmount();
            accountBalanceMap.replace(accountRequest.getAccountNumber(), balance);
            return " "+accountRequest.getAmount() + " added to account " + accountRequest.getAccountNumber()
                    + "\n New balance is: " + balance;
        } else {
            return "Invalid amount";
        }
    }
//    @GetMapping("depositMoney/{accountNr}/{amount}")
//    public static String depositMoney(@PathVariable("accountNr") String accountNr,
//                                      @PathVariable("amount") double amount) {
//        if (amount > 0) {
//            double balance = accountBalanceMap.get(accountNr);
//            balance = balance + amount;
//            accountBalanceMap.replace(accountNr, balance);
//            return amount + " added to account " + accountNr + " new balance is: " + balance;
//        } else {
//            return "Invalid amount";
//        }
//    }


    @PutMapping("/withdrawMoney")
    public static String withdrawMoney(@RequestBody AccountRequest accountRequest){
        if (accountRequest.getAmount() > 0) {
            double balance = accountBalanceMap.get(accountRequest.getAccountNumber());
            if (balance >= accountRequest.getAmount()) {
                balance = balance - accountRequest.getAmount();
                accountBalanceMap.replace(accountRequest.getAccountNumber(), balance);
                return " "+accountRequest.getAmount()+ " is withdrawn, new balance is: " + balance;
            } else {
                return "Not enough money on account";
            }
        } else {
            return "Invalid amount";
        }
    }

    @PutMapping("transfer/{firstAccountNr}")
    public static String transfer(@PathVariable("firstAccountNr") String firstAccountNr,
                                  @RequestBody AccountRequest accountRequest) {
        double firstAccountBalance = accountBalanceMap.get(firstAccountNr);
        if (firstAccountBalance >= accountRequest.getAmount()) {
            firstAccountBalance = firstAccountBalance - accountRequest.getAmount();
            accountBalanceMap.replace(firstAccountNr, firstAccountBalance);

            double secondAccountBalance = accountBalanceMap.get(accountRequest.getAccountNumber());
            secondAccountBalance = secondAccountBalance + accountRequest.getAmount();
            accountBalanceMap.replace(accountRequest.getAccountNumber(), secondAccountBalance);
            return accountRequest.getAmount() + " is transferred from " + firstAccountNr +
                    " to " + accountRequest.getAccountNumber();
        } else {
            return "Not enough money on account nr: " + firstAccountNr;
        }

    }


}
