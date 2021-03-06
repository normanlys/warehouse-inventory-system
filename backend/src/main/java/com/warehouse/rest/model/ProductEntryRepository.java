package com.warehouse.rest.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductEntryRepository extends CrudRepository<ProductEntry, ProductEntryId> {
    @Query("select p from ProductEntry p where p.code = ?1")
    List<ProductEntry> findByCode(String code);
}
