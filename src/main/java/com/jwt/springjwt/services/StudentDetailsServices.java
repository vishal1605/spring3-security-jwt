package com.jwt.springjwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.springjwt.StudentTempDb.StudentTempDb;
import com.jwt.springjwt.entity.Student;

@Service
public class StudentDetailsServices implements UserDetailsService {

    @Autowired
    private StudentTempDb repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = repo.findByEmail(username);
		if(student==null) {
			throw new UsernameNotFoundException("User Not Found"+username);
		}
		return student;
    }
    
}
