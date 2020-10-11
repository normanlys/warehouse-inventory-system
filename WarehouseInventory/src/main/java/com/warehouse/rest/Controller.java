package com.warehouse.rest;

import com.warehouse.rest.model.*;
import com.warehouse.rest.json.*;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class Controller 
{

    private ProductEntryRepository repository;

    public Controller(ProductEntryRepository repository)
    {
        this.repository = repository;

        // test data
        ProductEntry p0 = new ProductEntry("p0", "tko", "product0", 5);
        // ProductEntry p0y = new ProductEntry("p0y", "tsing yi", "product0", 544);
        // ProductEntry p1 = new ProductEntry("p1", "tko", "product1", 15);

        repository.save(p0);
        // repository.save(p1);
        // repository.save(p0y);
    }
    
    @GetMapping("/")
    ResponseEntity<String> greeting() 
    {
        return ResponseEntity.ok("hello world");
    }


    @GetMapping("/product-count/{code}")
    ResponseEntity<List<GetProductCountResponse>> getProductCount(@PathVariable String code)
    {
        List<ProductEntry> entries = repository.findByCode(code);
        List<GetProductCountResponse> result = entries.stream()
            .map( e -> new GetProductCountResponse(e.location, e.weight))
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(result);
    }

    @CrossOrigin(origins = "*")
	@PutMapping("/product-entry")
    ResponseEntity<?> createProductEntry(@Valid @RequestBody ProductEntry productEntry)
    {
        ProductEntryId id = new ProductEntryId(productEntry.code, productEntry.location);
        Optional<ProductEntry> existingEntry = repository.findById(id);
        if (existingEntry.isPresent())
        {
            ProductEntry newValue = existingEntry.get();
            newValue.weight += productEntry.weight;
            repository.save(newValue);
        } else {
            repository.save(productEntry);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @PostMapping("/product-entry")
    // ResponseEntity<?> moveProductEntry(@Validated @RequestBody )
    // {

    // }
}
