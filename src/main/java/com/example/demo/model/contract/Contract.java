package com.example.demo.model.contract;

import com.example.demo.model.chat.Chat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Contract")
public class Contract {
    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;

    @OneToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
    @Column(name = "contract_date")
    private LocalDate contractDate;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "contract_status")
    @Enumerated(value = EnumType.ORDINAL)
    private ContractType contractStatus;

}
