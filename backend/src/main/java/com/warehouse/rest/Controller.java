package com.warehouse.rest;

import com.warehouse.rest.model.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class Controller 
{

    private ProductEntryRepository entryRepository;
    private ProductRepository productRepository;

    public Controller(ProductEntryRepository entryRepository, ProductRepository productRepository)
    {
        this.entryRepository = entryRepository;
        this.productRepository = productRepository;
    }
    
    @GetMapping("/")
    ResponseEntity<String> greeting() 
    {
        return ResponseEntity.ok("hello world");
    }


    @GetMapping("/product-count/{code}")
    ResponseEntity<List<ProductCount>> getProductCount(@PathVariable String code)
    {
        List<ProductEntry> entries = entryRepository.findByCode(code);
        List<ProductCount> result = entries.stream()
            .map( e -> new ProductCount(e.location, e.weight))
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(result);
    }

    @CrossOrigin(origins = "*")
	@PutMapping("/product-entry")
    ResponseEntity<?> createProductEntry(@Valid @RequestBody List<@Valid ProductEntryUpdate> productEntryUpdates)
    {
        productEntryUpdates.stream()
            .forEach( productEntryUpdate -> {
                ProductEntryId id = new ProductEntryId(productEntryUpdate.code, productEntryUpdate.location);
                Optional<ProductEntry> existingEntry = entryRepository.findById(id);
                if (existingEntry.isPresent())
                {
                    ProductEntry newValue = existingEntry.get();
                    newValue.weight += productEntryUpdate.weight;
                    entryRepository.save(newValue);
                } 
                else 
                {
                    entryRepository.save(new ProductEntry(id, productEntryUpdate.weight));
                }

                Optional<Product> existingProduct = productRepository.findById(productEntryUpdate.code);
                if (existingProduct.isPresent())
                {
                    Product value = existingProduct.get();
                    if (value.name != productEntryUpdate.name)
                    {
                        value.name = productEntryUpdate.name;
                        productRepository.save(value);
                    }
                } 
                else 
                {
                    Product newProduct = new Product(productEntryUpdate.code, productEntryUpdate.name);
                    productRepository.save(newProduct);
                }
            });
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/product-entry")
    ResponseEntity<?> moveProductEntry(@Valid @RequestBody MoveProductEntry moveEntry) throws Exception
    {
        ProductEntryId fromId = new ProductEntryId(moveEntry.code, moveEntry.fromLocation);
        Optional<ProductEntry> fromEntry = entryRepository.findById(fromId);

        ProductEntryId toId = new ProductEntryId(moveEntry.code, moveEntry.toLocation);
        Optional<ProductEntry> toEntry = entryRepository.findById(toId);

        if (fromEntry.isPresent())
        {
            ProductEntry fromValue = fromEntry.get();
            Integer diff = Math.min(moveEntry.weight, fromValue.weight);
            fromValue.weight -= diff;
            entryRepository.save(fromValue);

            if (toEntry.isPresent()) {
                ProductEntry toValue = toEntry.get();
                toValue.weight += diff;
                entryRepository.save(toValue);
            } else {
                ProductEntry newEntry = new ProductEntry(toId, diff);
                entryRepository.save(newEntry);
            }

        } else {
            return ResponseEntity.badRequest().body("Product not exist in fromLocation");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
