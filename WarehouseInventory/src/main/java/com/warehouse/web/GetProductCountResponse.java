package com.warehouse.web;

import java.io.Serializable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetProductCountResponse implements Serializable 
{
    private static final long serialVersionUID = -2033400011313800667L;

    final String location;
    final Integer weight;    

    GetProductCountResponse(String location, Integer weight)
    {
        this.location = location;
        this.weight = weight;
    }
}
