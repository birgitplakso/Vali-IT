package ee.bcs.valiit.solution.repository;


import ee.bcs.valiit.controller.BankController;
import ee.bcs.valiit.sample.AccountRequest;
import ee.bcs.valiit.sample.AccountRequestRowMapper;
import ee.bcs.valiit.sample.TransactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createAccount(AccountRequest accountRequest) {


        String sql = "insert into bank_table(account_number, customer_name, account_balance, is_blocked) " +
                "values (:dbAccNr, :dbCustomerName, :dbAmount, :dbBlocked)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountRequest.getAccountNumber());
        paramMap.put("dbCustomerName", accountRequest.getOwnerName());
        paramMap.put("dbAmount", accountRequest.getAmount());
        paramMap.put("dbBlocked", accountRequest.isBlocked());
        jdbcTemplate.update(sql, paramMap);

    }

    public void saveTransaction(String accountNo, double balance) {
        String transaction = "insert into transaction_history(account_number, account_balance, transfer_time) " +
                "values (:dbAccNr, :dbAmount, :dbTime)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountNo);
        paramMap.put("dbAmount", balance);
        paramMap.put("dbTime", LocalDateTime.now());
        jdbcTemplate.update(transaction, paramMap);

    }

    public void blockAccount(AccountRequest accountRequest) {

        String sql = "update bank_table set is_blocked='true' where account_number= :dbAccNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountRequest.getAccountNumber());
        jdbcTemplate.update(sql, paramMap);
    }

    public void unBlockAccount(AccountRequest accountRequest) {
        String sql = "update bank_table set is_blocked='false' where account_number= :dbAccNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountRequest.getAccountNumber());
        jdbcTemplate.update(sql, paramMap);
    }

    public double getBalance(String accountNr) {
        String sql = "select account_balance from bank_table where account_number=:dbAccNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountNr);
        Double dbBalance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);
        return dbBalance;
    }

    public boolean isBlocked(String accountNo) {
        String block = "select is_blocked from bank_table where account_number=:dbAccNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountNo);
        Boolean dbBlocked = jdbcTemplate.queryForObject(block, paramMap, Boolean.class);
        return dbBlocked;
    }

    public void setNewBalance(String accountNo, double dbBalance) {
        String sql1 = "update bank_table set account_balance=:dbBalance where account_number=:dbAccNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNr", accountNo);
        paramMap.put("dbBalance", dbBalance);
        jdbcTemplate.update(sql1, paramMap);

    }

    public List<AccountRequest> getAllAccounts() {
        String sql = "select * from bank_table";
        return jdbcTemplate.query(sql, new HashMap<>(), new AccountRequestRowMapper());
    }

    public List<TransactionData> getTransactions(String accountNo) {
        String transactions = "select * from transaction_history where account_number=:dbAccNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNo", accountNo);

        return jdbcTemplate.query(transactions, paramMap, new BankController.TransactionDataRowMapper());
    }

}
