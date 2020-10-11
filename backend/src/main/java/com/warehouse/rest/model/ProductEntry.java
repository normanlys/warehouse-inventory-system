package com.warehouse.rest.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@Entity
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

    @PositiveOrZero(message = "weight must be positive or zero")
    @NonNull
    public Integer weight;

    public ProductEntry() {}

    public ProductEntry(ProductEntryId id, Integer weight)
    {
        this.code = id.code;
        this.location = id.location;
        this.weight = weight;
    }

    public ProductEntry(String code, String location, Integer weight)
    {
        this.code = code;
        this.location = location;
        this.weight = weight;
    }
}