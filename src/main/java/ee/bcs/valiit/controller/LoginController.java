package ee.bcs.valiit.controller;

import ee.bcs.valiit.solution.service.LoginService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("api")
@RestController
public class LoginController {

    @Autowired
    public LoginService loginService;

    @PutMapping("/public/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);

    }

    @PostMapping("/public/createUser")
    public String createUser(@RequestBody LoginRequest loginRequest){
        return loginService.createUser(loginRequest);
    }
}
