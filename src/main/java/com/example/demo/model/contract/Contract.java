package com.example.demo.model.contract;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Contract")
public class Contract {
    @Id
    private int contract_id;
    @Id
    private int chat_id;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean contract_status;

    @Column(nullable = false)
    private Date contract_date;
    private Date start_date;
    private Date end_date;
}
