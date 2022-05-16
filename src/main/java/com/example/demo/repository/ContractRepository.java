package com.example.demo.repository;

import com.example.demo.model.contract.Contract;
import com.example.demo.model.contract.ContractIBorrowedResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

    @Query(name = "ContractIBorrowedByClientIndex", nativeQuery = true)
    public Slice<ContractIBorrowedResponseDTO> findContractsByClientIndex(@Param("client_index") Integer clientIndex, Pageable page);

}
