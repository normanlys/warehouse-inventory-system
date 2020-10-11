package com.warehouse.rest.model;

import java.io.Serializable;

public class ProductCount implements Serializable 
{
    private static final long serialVersionUID = -2033400011313800667L;

    public String location;
    public Integer weight;    

    public ProductCount(String location, Integer weight)
    {
        this.location = location;
        this.weight = weight;
    }
}
