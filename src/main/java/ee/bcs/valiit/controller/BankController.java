package ee.bcs.valiit.controller;

import ee.bcs.valiit.sample.AccountRequest;
import ee.bcs.valiit.sample.AccountRequestRowMapper;
import ee.bcs.valiit.sample.TransactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BankController {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static HashMap<String, AccountRequest> accounts = new HashMap<>();


    // http://localhost:8080/createBankAccount
    @PostMapping("/createBankAccount")
    public String createAccount(@RequestBody AccountRequest accountRequest) {

        accounts.put(accountRequest.getAccountNumber(), accountRequest);
        String sql = "insert into bank_table(account_number, customer_name, account_balance, is_blocked) " +
                "values (:dbAccNr, :dbCustomerName, :dbAmount, :dbBlocked)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountRequest.getAccountNumber());
        paramMap.put("dbCustomerName", accountRequest.getOwnerName());
        paramMap.put("dbAmount", accountRequest.getAmount());
        paramMap.put("dbBlocked", accountRequest.isBlocked());

        String transaction = "insert into transaction_history(account_number, account_balance, transfer_time) " +
                "values (:dbAccNr, :dbAmount, :dbTime)";
        paramMap.put("dbTime", LocalDateTime.now());

        jdbcTemplate.update(sql, paramMap);
        jdbcTemplate.update(transaction, paramMap);


        return "Account " + accountRequest.getAccountNumber() + " is created";

    }


    @PutMapping("/blockAccount")
    public String blockAccount(@RequestBody AccountRequest accountRequest) {
        String sql = "update bank_table set is_blocked='true' where account_number= :dbAccNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountRequest.getAccountNumber());


        jdbcTemplate.update(sql, paramMap);

        return accountRequest.getAccountNumber() + " is blocked.";
        //accounts.get(accountRequest.getAccountNumber()).blockAccount();
        // accountRequest.getAccountNumber()+" is blocked.";
    }

    @PutMapping("/unBlockAccount")
    public String unBlockAccount(@RequestBody AccountRequest accountRequest) {
        String sql = "update bank_table set is_blocked='false' where account_number= :dbAccNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountRequest.getAccountNumber());
        jdbcTemplate.update(sql, paramMap);
        return accountRequest.getAccountNumber() + " is unblocked.";

    }

    @GetMapping("getAccountBalance/{accountNr}")
    public String getBalance(@PathVariable("accountNr") String accountNr) {
        AccountRequest accountRequest = accounts.get(accountNr);
        String sql = "select account_balance from bank_table where account_number=:dbAccNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountNr);
        Double dbBalance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);
        return "Your balance is " + dbBalance;

//          double balance=accountRequest.getAmount();
//        if(accountRequest.isBlocked()){
//            return "Account is blocked!";
//        }
//        return "Your balance is " +balance;
    }

    @PutMapping("/deposit")
    public String depositMoney(@RequestBody AccountRequest accountRequest) {
        if (accountRequest.getAmount() > 0) {
            String sql = "select account_balance from bank_table where account_number=:dbAccNr";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("dbAccNr", accountRequest.getAccountNumber());
            Double dbBalance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);

            String block = "select is_blocked from bank_table where account_number=:dbAccNr";
            Boolean dbBlocked = jdbcTemplate.queryForObject(block, paramMap, Boolean.class);
            if (dbBlocked) {
                return "Account is blocked!";
            } else {
                dbBalance = dbBalance + accountRequest.getAmount();
                String sql1 = "update bank_table set account_balance=:dbBalance where account_number=:dbAccNr";
                paramMap.put("dbBalance", dbBalance);
                jdbcTemplate.update(sql1, paramMap);

                String transaction = "insert into transaction_history(account_number, account_balance, transfer_time) " +
                        "values (:dbAccNr, :dbBalance, :dbTime)";
                paramMap.put("dbTime", LocalDateTime.now());
                jdbcTemplate.update(transaction, paramMap);

                return "New balance is: " + dbBalance;
            }
        } else {
            return "Invalid amount";
            // AccountRequest customerData = accounts.get(accountRequest.getAccountNumber());
            // double balance = customerData.getAmount();
            //          if (customerData.isBlocked()) {
            //             return "Account is blocked!";
            //        } else {
            //balance = balance + accountRequest.getAmount();
            //customerData.setAmount(balance);
            //accounts.replace(accountRequest.getAccountNumber(), customerData);

            //  return " "+accountRequest.getAmount() + " added to account " + accountRequest.getAccountNumber()
            //                        + "\n New balance is: " + balance;
            //           }
            //        }
            // }
            // return null;
        }
    }

    @PutMapping("/withdraw")
    public String withdrawMoney(@RequestBody AccountRequest accountRequest) {

        if (accountRequest.getAmount() > 0) {
            String sql = "select account_balance from bank_table where account_number=:dbAccNr";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("dbAccNr", accountRequest.getAccountNumber());
            Double dbBalance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);
            String sql1 = "select is_blocked from bank_table where account_number=:dbAccNr";
            Boolean dbBlocked = jdbcTemplate.queryForObject(sql1, paramMap, Boolean.class);
            if (dbBlocked) {
                return "Account is blocked!";
            } else if (dbBalance >= accountRequest.getAmount()) {
                dbBalance = dbBalance - accountRequest.getAmount();
                String sql2 = "update bank_table set account_balance=:dbBalance where account_number=:dbAccNr";
                paramMap.put("dbBalance", dbBalance);
                jdbcTemplate.update(sql2, paramMap);

                String transaction = "insert into transaction_history(account_number, account_balance, transfer_time) " +
                        "values (:dbAccNr, :dbBalance, :dbTime)";
                paramMap.put("dbTime", LocalDateTime.now());
                jdbcTemplate.update(transaction, paramMap);

                return " " + accountRequest.getAmount() + " is withdrawn from "
                        + accountRequest.getAccountNumber() + ", new balance is: " + dbBalance;
            } else {
                return "Not enough money on account";
            }
        } else {
            return "Invalid amount";

//            AccountRequest customerData = accounts.get(accountRequest.getAccountNumber());
//            double balance = customerData.getAmount();
//            } if (customerData.isBlocked()) {
//                return "Account is blocked!";
//            } else if (balance >= accountRequest.getAmount()) {
//                balance = balance - accountRequest.getAmount();
//                customerData.setAmount(balance);
//                accounts.replace(accountRequest.getAccountNumber(), customerData);
//                return " " + accountRequest.getAmount() + " is withdrawn, new balance is: " + balance;
//            } else {
//                return "Not enough money on account";
//            }
//        } else {
//            return "Invalid amount";
        }
    }

    @PutMapping("transferMoney/{firstAccountNr}")
    public String transfer(@PathVariable("firstAccountNr") String firstAccountNr,
                           @RequestBody AccountRequest accountRequest) {
        String sql = "select account_balance from bank_table where account_number=:dbAccNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", firstAccountNr);

        Double dbFirstAccBalance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);


        String isBlocked1 = "select is_blocked from bank_table where account_number=:dbAccNr";
        Boolean dbBlocked1 = jdbcTemplate.queryForObject(isBlocked1, paramMap, Boolean.class);
        String sql2 = "select account_balance from bank_table where account_number=:dbAccNr2";
        paramMap.put("dbAccNr2", accountRequest.getAccountNumber());
        String isBlocked2 = "select is_blocked from bank_table where account_number=:dbAccNr2";
        Boolean dbBlocked2 = jdbcTemplate.queryForObject(isBlocked2, paramMap, Boolean.class);
        if (dbBlocked1 || dbBlocked2) {
            return "Blocked account, can't proceed with transaction!";
        } else if (dbFirstAccBalance >= accountRequest.getAmount()) {

            dbFirstAccBalance = dbFirstAccBalance - accountRequest.getAmount();

            String sql1 = "update bank_table set account_balance=:dbBalance1 where account_number=:dbAccNr";
            paramMap.put("dbBalance1", dbFirstAccBalance);

            jdbcTemplate.update(sql1, paramMap);

            Double secondAccBalance = jdbcTemplate.queryForObject(sql2, paramMap, Double.class);
            secondAccBalance = secondAccBalance + accountRequest.getAmount();
            String sql3 = "update bank_table set account_balance=:dbBalance2 where account_number=:dbAccNr2";
            paramMap.put("dbBalance2", secondAccBalance);
            jdbcTemplate.update(sql3, paramMap);

            String transaction = "insert into transaction_history(account_number, account_balance, transfer_time) " +
                    "values (:dbAccNr, :dbBalance1, :dbTime)";
            paramMap.put("dbTime", LocalDateTime.now());
            jdbcTemplate.update(transaction, paramMap);

            String transaction2 = "insert into transaction_history(account_number, account_balance, transfer_time) " +
                    "values (:dbAccNr2, :dbBalance2, :dbTime)";

            jdbcTemplate.update(transaction2, paramMap);

            return accountRequest.getAmount() + " is transferred from " + firstAccountNr +
                    " to " + accountRequest.getAccountNumber();
        } else {
            return "Not enough money on account nr: " + firstAccountNr;
        }
//        AccountRequest firstCustomerData = accounts.get(firstAccountNr);
//        double firstAccountBalance = firstCustomerData.getAmount();
//        if (firstCustomerData.isBlocked()) {
//            return "The account you want to transfer from, is blocked!";
//        } else if (firstAccountBalance >= accountRequest.getAmount()) {
//            firstAccountBalance = firstAccountBalance - accountRequest.getAmount();
//            firstCustomerData.setAmount(firstAccountBalance);
//            accounts.replace(firstAccountNr, firstCustomerData);
//
//            AccountRequest secondCustomerData = accounts.get(accountRequest.getAccountNumber());
//            double secondAccountBalance = secondCustomerData.getAmount();
//
//            if (secondCustomerData.isBlocked()) { //account value -1 means blocked account
//                return "The account you want to transfer to, is blocked!";
//          Üles poole see boolean!!
//            }
//            secondAccountBalance = secondAccountBalance + accountRequest.getAmount();
//            secondCustomerData.setAmount(secondAccountBalance);
//            accounts.replace(accountRequest.getAccountNumber(), secondCustomerData);
//            return accountRequest.getAmount() + " is transferred from " + firstAccountNr +
//                    " to " + accountRequest.getAccountNumber();
//        } else {
//            return "Not enough money on account nr: " + firstAccountNr;
//        }

    }

    // http://localhost:8080/accountList
    @GetMapping("accountList")
    public List<AccountRequest> getAllAccounts() {
        String sql = "select * from bank_table";
        return jdbcTemplate.query(sql, new HashMap<>(), new AccountRequestRowMapper());
    }

    @GetMapping("listOfTransactions")
    public List<TransactionData> getTransactions(@RequestBody TransactionData transactionData) {
        String transactions = "select * from transaction_history where account_number=:dbAccNo";
        Map<String, Object>paramMap=new HashMap<>();
        paramMap.put("dbAccNo",transactionData.getAccountNumber());

        return jdbcTemplate.query(transactions, paramMap, new TransactionDataRowMapper());
    }


    public class TransactionDataRowMapper implements RowMapper<TransactionData> {

        public TransactionData mapRow(ResultSet resultSet, int i) throws SQLException {
            TransactionData returnData = new TransactionData();
            returnData.setAccountNumber(resultSet.getString("account_balance"));
            returnData.setAccountBalance(resultSet.getDouble("account_balance"));
            returnData.setTransferTime(resultSet.getObject("transfer_time", LocalDateTime.class));

            return returnData;
        }


    }
}