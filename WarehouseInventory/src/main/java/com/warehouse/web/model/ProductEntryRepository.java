package com.warehouse.web.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductEntryRepository extends JpaRepository<ProductEntry, ProductEntryId> {
    @Query("select p from ProductEntry p where p.code = ?1")
    List<ProductEntry> findByCode(String code);
}
