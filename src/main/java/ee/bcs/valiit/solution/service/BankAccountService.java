package ee.bcs.valiit.solution.service;


import ee.bcs.valiit.sample.AccountRequest;
import ee.bcs.valiit.sample.AccountRequestRowMapper;
import ee.bcs.valiit.sample.TransactionData;
import ee.bcs.valiit.solution.exception.SampleApplicationException;
import ee.bcs.valiit.solution.exception.SampleErrorHandler;
import ee.bcs.valiit.solution.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankAccountService {

    @Autowired
    public AccountRepository accountRepository;

    public void createAccount(AccountRequest accountRequest) {
        if(accountRequest.getAccountNumber()==null || accountRequest.getAmount()<0){
            throw new SampleApplicationException("");
        }
        accountRepository.createAccount(accountRequest);
        accountRepository.saveTransaction(accountRequest.getAccountNumber(), accountRequest.getAmount());

    }

    public void blockAccount(AccountRequest accountRequest){
        accountRepository.blockAccount(accountRequest);
    }

    public void unBlockAccount(AccountRequest accountRequest){
        accountRepository.unBlockAccount(accountRequest);
    }

    public double getBalance(String accountNr){
         return accountRepository.getBalance(accountNr);
    }

    public String depositMoney(AccountRequest accountRequest){
        if (accountRequest.getAmount() > 0) {
            double dbBalance=accountRepository.getBalance(accountRequest.getAccountNumber());
            boolean dbBlocked= accountRepository.isBlocked(accountRequest.getAccountNumber());
            if (dbBlocked) {
                return "Account is blocked!";
            } else {
                dbBalance = dbBalance + accountRequest.getAmount();
                accountRepository.setNewBalance(accountRequest.getAccountNumber(),dbBalance);
                accountRepository.saveTransaction(accountRequest.getAccountNumber(), dbBalance);

                return "New balance is: " + dbBalance;
            }
        } else {
            return "Invalid amount";
        }
    }

    public String withdrawMoney(AccountRequest accountRequest){
        if (accountRequest.getAmount() > 0) {
            Double dbBalance= accountRepository.getBalance(accountRequest.getAccountNumber());
            Boolean dbBlocked = accountRepository.isBlocked(accountRequest.getAccountNumber());
            if (dbBlocked) {
                return "Account is blocked!";
            } else if (dbBalance >= accountRequest.getAmount()) {
                dbBalance = dbBalance - accountRequest.getAmount();
                accountRepository.setNewBalance(accountRequest.getAccountNumber(), dbBalance);
                accountRepository.saveTransaction(accountRequest.getAccountNumber(), dbBalance);
                return " " + accountRequest.getAmount() + " is withdrawn from "
                        + accountRequest.getAccountNumber() + ", new balance is: " + dbBalance;
            } else {
                return "Not enough money on account";
            }
        } else {
            return "Invalid amount";
        }
    }

    public String transfer(String firstAccNo, AccountRequest accountRequest){
        Double dbFirstAccBalance = accountRepository.getBalance(firstAccNo);
        Boolean dbBlocked1 =accountRepository.isBlocked(firstAccNo);
        Boolean dbBlocked2 = accountRepository.isBlocked(accountRequest.getAccountNumber());
        if (dbBlocked1 || dbBlocked2) {
            return "Blocked account, can't proceed with transaction!";
        } else if (dbFirstAccBalance >= accountRequest.getAmount()) {

            dbFirstAccBalance = dbFirstAccBalance - accountRequest.getAmount();

            accountRepository.setNewBalance(firstAccNo, dbFirstAccBalance);

            Double secondAccBalance = accountRepository.getBalance(accountRequest.getAccountNumber());
            secondAccBalance = secondAccBalance + accountRequest.getAmount();
            accountRepository.setNewBalance(accountRequest.getAccountNumber(),secondAccBalance);

            accountRepository.saveTransaction(firstAccNo, dbFirstAccBalance);
            accountRepository.saveTransaction(accountRequest.getAccountNumber(), secondAccBalance);

            return accountRequest.getAmount() + " is transferred from " + firstAccNo +
                    " to " + accountRequest.getAccountNumber();
        } else {
            return "Not enough money on account nr: " + firstAccNo;
        }
    }

    public List<AccountRequest> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    public List<TransactionData> getTransactions(String accountNo){
        return accountRepository.getTransactions(accountNo);
    }

}
