package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StudentRepositoryTest extends DemoApplicationTests{
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void create() {
        // User 생성
        Student user =
                Student.builder()
                        .studentId("11111111")
                        .firstName("sulma")
                        .secondName("dwena")
                        .department("WHY")
                        .build();
        // Create!
        studentRepository.save(user);
    }

    @Test
    public ArrayList<Student> read(){
        ArrayList<Student> sList = new ArrayList<>();
        studentRepository.findAll().forEach(e -> sList.add(e));
        return sList;
    }
}
