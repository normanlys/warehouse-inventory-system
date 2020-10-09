package com.warehouse.web;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "product_entry")
@IdClass(ProductEntryId.class)
public class ProductEntry
{
    @Id
    private String code;

    @Id
    private String location;

    @NonNull
    private String name;

    @NonNull
    private Integer weight;
}