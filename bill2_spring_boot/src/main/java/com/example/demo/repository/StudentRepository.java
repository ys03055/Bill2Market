package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // 인터페이스
    ArrayList<Student> findAll();
}
