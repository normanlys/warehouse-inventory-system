package com.warehouse.web;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller 
{

    private ProductEntryRepository repository;

    public Controller(ProductEntryRepository repository)
    {
        this.repository = repository;
    }

    @PutMapping("/product-entry")
    ResponseEntity<?> createProductEntry(@Validated @RequestBody ProductEntry productEntry)
    {
        repository.save(productEntry);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
