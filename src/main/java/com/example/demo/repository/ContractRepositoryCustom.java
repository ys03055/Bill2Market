package com.example.demo.repository;

import com.example.demo.model.contract.ContractScheduleDTO;
import com.example.demo.model.contract.ContractType;
import com.example.demo.model.contract.QContractScheduleDTO;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.example.demo.model.contract.QContract.contract;
import static com.example.demo.model.chat.QChat.chat;
import static com.example.demo.model.client.QClient.client;

@Repository
@RequiredArgsConstructor
public class ContractRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public List<ContractScheduleDTO> findByExpireContract(LocalDate endDate){
        return findByEndDateWithoutWhere().where(
                        dateDiff(endDate).eq(1),
                        contract.contractStatus.eq(ContractType.values()[1])
                ).fetch();
    }

    public List<ContractScheduleDTO> findByExpireBeforeContract(LocalDate endDate){
        return findByEndDateWithoutWhere().where(
                        contract.endDate.eq(endDate),
                        contract.contractStatus.eq(ContractType.values()[1])
                ).fetch();
    }

    private JPAQuery<ContractScheduleDTO> findByEndDateWithoutWhere(){
        return jpaQueryFactory.select(new QContractScheduleDTO(
                        contract.contractId.as("contractId"),
                        contract.chat.chatId.as("chatId"),
                        contract.chat.owner.clientIndex.as("senderId"),
                        contract.chat.owner.nickname.as("senderNickname")
                ))
                .from(contract)
                .innerJoin(contract.chat, chat)
                .on(chat.chatId.eq(chat.chatId))
                .innerJoin(chat.owner, client)
                .on(chat.owner.clientIndex.eq(client.clientIndex));
    }

    public void modifyState(Integer contractId, ContractType contractType){
        jpaQueryFactory.update(contract)
                .set(contract.contractStatus, contractType)
                .where(contract.contractId.eq(contractId))
                .execute();
    }

    private NumberTemplate<Integer> dateDiff(LocalDate endDate){
        return Expressions.numberTemplate(Integer.class,"DATEDIFF({0}, end_date)", endDate);
    }
}
