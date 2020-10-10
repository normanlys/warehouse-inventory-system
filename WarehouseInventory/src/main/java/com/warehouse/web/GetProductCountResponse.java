package com.warehouse.web;

import java.io.Serializable;

public class GetProductCountResponse implements Serializable 
{
    private static final long serialVersionUID = -2033400011313800667L;

    public String location;
    public Integer weight;    

    GetProductCountResponse(String location, Integer weight)
    {
        this.location = location;
        this.weight = weight;
    }
}
