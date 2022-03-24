package com.example.demo;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class Example {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value="/api/example", method= RequestMethod.GET)
    public List<String> example() {
        ArrayList<Student> sList = studentRepository.findAll();
        return Arrays.asList(sList.get(0).getStudentId(), sList.get(0).getFirstName(), sList.get(0).getSecondName());
    }
}
