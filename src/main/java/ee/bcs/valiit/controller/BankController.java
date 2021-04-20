package ee.bcs.valiit.controller;

import ee.bcs.valiit.sample.AccountRequest;
import ee.bcs.valiit.sample.AccountRequestRowMapper;
import ee.bcs.valiit.sample.TransactionData;
import ee.bcs.valiit.solution.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@RestController
public class BankController {

    @Autowired
    private BankAccountService bankAccountService;


    // http://localhost:8080/createBankAccount
    @PostMapping("/createBankAccount")
    public String createAccount(@RequestBody AccountRequest accountRequest) {
        bankAccountService.createAccount(accountRequest);
        return "Account " + accountRequest.getAccountNumber() + " is created";
    }


    @PutMapping("/blockAccount")
    public String blockAccount(@RequestBody AccountRequest accountRequest) {
        bankAccountService.blockAccount(accountRequest);
        return accountRequest.getAccountNumber() + " is blocked.";
    }

    @PutMapping("/unBlockAccount")
    public String unBlockAccount(@RequestBody AccountRequest accountRequest) {
        bankAccountService.unBlockAccount(accountRequest);
        return accountRequest.getAccountNumber() + " is unblocked.";

    }

    @GetMapping("getAccountBalance/{accountNr}")
    public String getBalance(@PathVariable("accountNr") String accountNr) {
        return "Your balance is " + bankAccountService.getBalance(accountNr);

    }

    @PutMapping("/deposit")
    public String depositMoney(@RequestBody AccountRequest accountRequest) {
        return bankAccountService.depositMoney(accountRequest);
    }


    @PutMapping("/withdraw")
    public String withdrawMoney(@RequestBody AccountRequest accountRequest) {
        return bankAccountService.withdrawMoney(accountRequest);
    }

    @PutMapping("transferMoney/{firstAccountNr}")
    public String transfer(@PathVariable("firstAccountNr") String firstAccountNr,
                           @RequestBody AccountRequest accountRequest) {
        return bankAccountService.transfer(firstAccountNr, accountRequest);
    }


    // http://localhost:8080/accountList
    @GetMapping("accountList")
    public List<AccountRequest> getAllAccounts() {
        return bankAccountService.getAllAccounts();
    }

    @GetMapping("listOfTransactions")
    public List<TransactionData> getTransactions(@RequestBody TransactionData transactionData) {
        return bankAccountService.getTransactions(transactionData.getAccountNumber());
    }


    public static class TransactionDataRowMapper implements RowMapper<TransactionData> {

        public TransactionData mapRow(ResultSet resultSet, int i) throws SQLException {
            TransactionData returnData = new TransactionData();
            returnData.setAccountNumber(resultSet.getString("account_number"));
            returnData.setAccountBalance(resultSet.getDouble("account_balance"));
            returnData.setTransferTime(resultSet.getObject("transfer_time", LocalDateTime.class));

            return returnData;
        }


    }
}