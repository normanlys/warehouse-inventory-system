package com.warehouse.web.model;

import java.io.Serializable;

public class JsonModel implements Serializable 
{
    private static final long serialVersionUID = -4224607229402163804L;
    
    public String id;

    public JsonModel(String id)
    {
        this.id = id;
    }
}
