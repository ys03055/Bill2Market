package com.example.demo.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Student")
public class Student {
    @Id
    private String studentId;
    @Column(nullable = false)
    private String firstName;
    private String secondName;
    private String department;
}
