package com.jwt.springjwt.StudentTempDb;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.jwt.springjwt.entity.Student;

@Component
public class StudentTempDb {
    
    List<Student> tempDb = new ArrayList<>();
    public StudentTempDb(){
        tempDb.add(new Student(UUID.randomUUID().toString(), "Vishalgt581@gmail.com", "9004", 24));
        tempDb.add(new Student(UUID.randomUUID().toString(), "Sahil777@gmail.com", new BCryptPasswordEncoder().encode("777"), 24));
        tempDb.add(new Student(UUID.randomUUID().toString(), "Nitesh911@gmail.com", new BCryptPasswordEncoder().encode("911"), 24));
        tempDb.add(new Student(UUID.randomUUID().toString(), "Ravi111@gmail.com", new BCryptPasswordEncoder().encode("111"), 24));
        tempDb.add(new Student(UUID.randomUUID().toString(), "Amit444@gmail.com", new BCryptPasswordEncoder().encode("444"), 24));
    }

    public Student findByEmail(String email){
        return tempDb.stream().filter(e-> e.getEmail().equals(email)).findFirst().get();
    }

    public List<Student> getAllStudents(){
        return tempDb;
    }
    
}
