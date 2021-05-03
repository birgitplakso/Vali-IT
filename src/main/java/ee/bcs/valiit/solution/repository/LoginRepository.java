package ee.bcs.valiit.solution.repository;

import ee.bcs.valiit.controller.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LoginRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public String getPassword(String username){
        String sql="select password from login_table where username =:dbUsername";
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("dbUsername",username);
        String password=jdbcTemplate.queryForObject(sql,paramMap,String.class);
        return password;

    }

    public void createUser(String username, String password){
        String sql="insert into login_table(username, password) values(:dbUsername, :dbPassword)";
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("dbUsername", username);
        paramMap.put("dbPassword", password);
        jdbcTemplate.update(sql, paramMap);
    }
}
