package com.warehouse.rest.model;

import java.io.Serializable;

public class ProductEntryId implements Serializable
{
    private static final long serialVersionUID = -2045670449563157442L;

    public String code;
    public String location;

    public ProductEntryId() {}
    
    public ProductEntryId(String code, String location)
    {
        this.code = code;
        this.location = location;
    }
}
