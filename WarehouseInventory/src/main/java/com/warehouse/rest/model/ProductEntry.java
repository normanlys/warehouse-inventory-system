package com.warehouse.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "product_entry")
@IdClass(ProductEntryId.class)
public class ProductEntry
{
    @Id
    public String code;

    @Id
    public String location;

    @NonNull
    public String name;

    @NonNull
    public Integer weight;

    public ProductEntry() {}

    public ProductEntry(ProductEntryId id, String name, Integer weight)
    {
        this.code = id.code;
        this.location = id.location;
        this.name = name;
        this.weight = weight;
    }

    public ProductEntry(String code, String location, String name, Integer weight)
    {
        this.code = code;
        this.location = location;
        this.name = name;
        this.weight = weight;
    }
}