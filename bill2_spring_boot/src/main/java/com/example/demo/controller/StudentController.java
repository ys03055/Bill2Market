package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value="/api/example", method= RequestMethod.GET)
    public List<String> example() {
        ArrayList<Student> sList = studentRepository.findAll();
        return Arrays.asList(sList.get(0).getStudentId(), sList.get(0).getFirstName(), sList.get(0).getSecondName());
    }
}
