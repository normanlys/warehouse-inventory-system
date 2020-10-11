package com.warehouse.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "product_entry")
@IdClass(ProductEntryId.class)
public class ProductEntry
{
    @NotBlank(message = "code is mandatory")
    @Id
    public String code;

    @NotBlank(message = "location is mandatory")
    @Id
    public String location;

    @NotBlank(message = "name is mandatory")
    @NonNull
    public String name;

    @PositiveOrZero(message = "weight must be positive or zero")
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