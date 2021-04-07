package com.example.demo.controllers;


import com.example.demo.model.Students;
import com.example.demo.repos.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    StudentsRepository studentsRepository;

    @PostMapping("/save")
    Students saveStudent(@RequestBody Students student){
        Students st=null;
        if(student.getStudentFirstname() ==null || student.getStudentLastname()==null || student.getRegNo()==null ){
            System.out.println("All fields are required!");
        }else{
            st=studentsRepository.save(student);
        }
        return st;
    }

    @GetMapping("/getStudent/{userid}")
    Optional<Students> getStdById(@PathVariable Long userid){

       return studentsRepository.findById(userid);
    }

    @DeleteMapping("/delete/{userid}")
    void deleStudent(@PathVariable Long userid){
        studentsRepository.deleteById(userid);
    }

    @PutMapping("/update")
    ResponseEntity<?> updateStudent(@RequestBody Students students){
        Long userId=students.getStudentId();
        Optional<Object> std= studentsRepository.findById(userId).map(
                students1 -> {
                    students1.setStudentFirstname(students.getStudentFirstname());
                    students1.setStudentLastname(students.getStudentLastname());
                    students1.setRegNo(students.getRegNo());
                    return studentsRepository.save(students);
                }
        );
        return new ResponseEntity(std, HttpStatus.CREATED);
    }
}
