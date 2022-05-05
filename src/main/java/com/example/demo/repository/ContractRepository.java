package com.example.demo.repository;

import com.example.demo.model.contract.Contract;
import com.example.demo.model.contract.ContractMeListResponseDTO;
import com.example.demo.model.item.SimpleItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

    @Query(name = ItemIBorrowedByClientId, nativeQuery = true)
    public Slice<ContractMeListResponseDTO> findContractsByClientIndex(@Param("client_index") Integer clientIndex, Pageable page);

}
