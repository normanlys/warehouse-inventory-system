package com.warehouse.web.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class ProductEntryId implements Serializable
{
    private static final long serialVersionUID = -2045670449563157442L;

    @NonNull
    public String code;
    @NonNull
    public String location;
}
