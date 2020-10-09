package com.warehouse.web;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntryRepository extends JpaRepository<ProductEntry, Long> {
    
}
