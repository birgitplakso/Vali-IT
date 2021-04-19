package ee.bcs.valiit.sample;



import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRequestRowMapper implements RowMapper<AccountRequest> {

    @Override
    public AccountRequest mapRow(ResultSet resultSet, int i)throws SQLException{
        AccountRequest response=new AccountRequest();
        response.setAccountNumber(resultSet.getString("account_number"));
        response.setAmount(resultSet.getDouble("account_balance"));
        response.setOwnerName(resultSet.getString("customer_name"));
        response.setBlocked(resultSet.getBoolean("is_blocked"));

        return response;
    }
}
