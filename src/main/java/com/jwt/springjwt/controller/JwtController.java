package com.jwt.springjwt.controller;

import java.security.Principal;

import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.springjwt.StudentTempDb.StudentTempDb;
import com.jwt.springjwt.config.JwtHelper;
import com.jwt.springjwt.entity.JwtRequest;
import com.jwt.springjwt.entity.JwtResponse;
import com.jwt.springjwt.services.StudentDetailsServices;

@RestController
@RequestMapping("/api")
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private StudentDetailsServices studentDetailsServices;


    @Autowired
    private StudentTempDb studentTempDb;
    

    @PostMapping("/login-process")
    public ResponseEntity<?> user(@RequestBody JwtRequest jwtRequest){
       try {
         this.doAuthentication(jwtRequest.getEmail(), jwtRequest.getPassword());


        UserDetails userDetails = studentDetailsServices.loadUserByUsername(jwtRequest.getEmail());
        String token = this.jwtHelper.generateToken(userDetails);
        System.out.println(token);

        JwtResponse response = new JwtResponse();
        response.setJwtToken(token);
        response.setEmail(userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
       } catch (Exception e) {
        return new ResponseEntity<>("Bad Credicial", HttpStatus.OK);
       }
        
    }

    @GetMapping("/demo")
    public String demo(){

        return "Demo";
    }

    @GetMapping("/hello")
    public ResponseEntity<?> procted(Principal principal){

        return new ResponseEntity<>(principal, HttpStatus.OK);
    }

    private void doAuthentication(String email, String password){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    
}
