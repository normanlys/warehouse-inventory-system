package com.warehouse.rest.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
    @NotBlank(message = "code cannot be blank")
    @Id
    private String code;

    @NotBlank(message = "name cannot be blank")
    public String name;

    private Product() {}

    public Product(String code, String name)
    {
        this.code = code;
        this.name = name;
    }
}
