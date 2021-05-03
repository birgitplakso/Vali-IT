package ee.bcs.valiit.solution.service;

import ee.bcs.valiit.controller.LoginRequest;
import ee.bcs.valiit.solution.exception.SampleApplicationException;
import ee.bcs.valiit.solution.repository.LoginRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginRepository loginRepository;

    public String login(LoginRequest loginRequest){
        String databasePassword=loginRepository.getPassword(loginRequest.getUsername());
        if(passwordEncoder.matches(loginRequest.getPassword(), databasePassword)){
            //matches on Password encoder'i fn mis võrdleb kodeeritud ja kodeerimatta salasõna
            Date today = new Date();
            Date tokenExpirationDate=new Date(today.getTime()+1000*60*60*24); //login kehtib 24h
            JwtBuilder jwtBuilder= Jwts.builder()       //tokeni genereerimine
                    .setExpiration(tokenExpirationDate)         //tokeni kehtivus
                    .setIssuedAt(today)
                    .signWith(SignatureAlgorithm.HS256, "c2VjcmV0")
                    .claim("username", loginRequest.getUsername());
            return jwtBuilder.compact();                        //tagastab tokeni
        }else{
            throw new SampleApplicationException("Error, insert valid data");
        }
    }

    public String createUser(LoginRequest loginRequest) {
        String encodedPassword = passwordEncoder.encode(loginRequest.getPassword());
        //kodeerime salasõna andmebaasi jaoks ümber
        try {
            loginRepository.createUser(loginRequest.getUsername(),encodedPassword);
        }catch(EmptyResultDataAccessException e){
            throw new SampleApplicationException("This username already exists");
            }
        return "New user "+loginRequest.getUsername()+" is created!";

    }
}
