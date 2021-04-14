package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class Controller4Ex {

    private static HashMap<String, Double> accountBalanceMap = new HashMap<>();


    @GetMapping("createAccount/{accountNr}")
    public static String createAccount(@PathVariable("accountNr") String accountNr) {
        accountBalanceMap.put(accountNr, 0.0);
        return "Account "+accountNr+" is created";

    }

    @GetMapping("getBalance/{accountNr}")
    public static String getBalance(@PathVariable("accountNr") String accountNr) {
        return "Your balance is " + accountBalanceMap.get(accountNr);
    }

@GetMapping("depositMoney/{accountNr}/{amount}")
    public static String depositMoney(@PathVariable("accountNr") String accountNr,
                                      @PathVariable("amount") double amount) {
        if (amount > 0) {
            double balance = accountBalanceMap.get(accountNr);
            balance = balance + amount;
            accountBalanceMap.replace(accountNr, balance);
            return amount + " added to account " + accountNr + " new balance is: " + balance;
        } else {
            return "Invalid amount";
        }
    }

    @GetMapping("withdrawMoney/{accountNr}/{amount}")
    public static String withdrawMoney(@PathVariable("accountNr") String accountNr,
                                       @PathVariable("amount") double amount) {
        if (amount > 0) {
            double balance = accountBalanceMap.get(accountNr);
            if (balance >= amount) {
                balance = balance - amount;
                accountBalanceMap.replace(accountNr, balance);
                return amount + " is withdrawn, new balance is: " + balance;
            } else {
                return "Not enough money on account";
            }
        } else {
            return "Invalid amount";
        }
    }

    @GetMapping("transfer/{firstAccountNr}/{secondAccountNr}/{amount}")
    public static String transfer(@PathVariable("firstAccountNr") String firstAccountNr,
                                  @PathVariable("secondAccountNr") String secondAccountNr,
                                  @PathVariable("amount") double amountToTransfer) {
        double firstAccountBalance = accountBalanceMap.get(firstAccountNr);
        if (firstAccountBalance >= amountToTransfer) {
            firstAccountBalance = firstAccountBalance - amountToTransfer;
            accountBalanceMap.replace(firstAccountNr, firstAccountBalance);

            double secondAccountBalance = accountBalanceMap.get(secondAccountNr);
            secondAccountBalance = secondAccountBalance + amountToTransfer;
            accountBalanceMap.replace(secondAccountNr, secondAccountBalance);
            return amountToTransfer + " is transferred from " + firstAccountNr + " to " + secondAccountNr;
        } else {
            return "Not enough money on account nr: " + firstAccountNr;
        }

    }
}
