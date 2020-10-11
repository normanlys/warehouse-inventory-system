package com.warehouse.rest.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class ProductEntryUpdate {
    @NotBlank
    public String code;
    @NotBlank
    public String location;
    @NotBlank
    public String name;
    @PositiveOrZero
    public Integer weight;

    public ProductEntryUpdate(String code, String location, String name, Integer weight)
    {
        this.code = code;
        this.location = location;
        this.name = name;
        this.weight = weight;
    }
}
