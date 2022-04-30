package com.example.demo.repository;

import com.example.demo.model.item.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query(name = "SimpleItemSliceByLocation", nativeQuery = true)
    public Slice<SimpleItem> findAllByLocation(@Param("client_longitude") double clientLongitude, @Param("client_latitude") double clientLatitude, @Param("client_index") Integer clientIndex, Pageable pageable);

    @Query(name = "ItemOwnerByOwnerId", nativeQuery = true)
    public List<ItemOwnerResponseDTO> findByOwnerId(@Param("owner_id") Integer ownerId, Pageable pageable);
}