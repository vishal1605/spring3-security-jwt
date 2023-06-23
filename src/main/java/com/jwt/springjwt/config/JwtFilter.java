package com.jwt.springjwt.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.springjwt.services.StudentDetailsServices;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtService;


    @Autowired
    private StudentDetailsServices userInfoUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
         String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            System.out.println("Got An Token from User");
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
            System.out.println(username);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
            System.out.println("||||||||||||||||||||||||||||||||||||||||");
            UserDetails userDetails = userInfoUserDetailsService.loadUserByUsername(username);
            System.out.println(userDetails);
            if(jwtService.isValidToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                System.out.println("AuthToken :"+authToken);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        System.out.println(")))))))))))))))))))))))))))))))))))))))))))))))))))))))))");
        filterChain.doFilter(request, response);
    }
    
}
