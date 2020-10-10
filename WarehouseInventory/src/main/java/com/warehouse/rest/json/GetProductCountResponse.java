package com.warehouse.rest.json;

import java.io.Serializable;

public class GetProductCountResponse implements Serializable 
{
    private static final long serialVersionUID = -2033400011313800667L;

    public String location;
    public Integer weight;    

    public GetProductCountResponse(String location, Integer weight)
    {
        this.location = location;
        this.weight = weight;
    }
}
