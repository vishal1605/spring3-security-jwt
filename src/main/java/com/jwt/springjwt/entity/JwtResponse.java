package com.jwt.springjwt.entity;

public class JwtResponse {
    private String jwtToken;
    private String email;
    public String getJwtToken() {
        return jwtToken;
    }
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public JwtResponse(String jwtToken, String email) {
        this.jwtToken = jwtToken;
        this.email = email;
    }
    public JwtResponse() {
    }
    @Override
    public String toString() {
        return "JwtResponse [jwtToken=" + jwtToken + ", email=" + email + "]";
    }

    
}
