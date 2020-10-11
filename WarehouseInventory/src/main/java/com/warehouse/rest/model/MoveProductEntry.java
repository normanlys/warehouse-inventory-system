package com.warehouse.rest.model;

import javax.validation.constraints.*;

public class MoveProductEntry {
    @NotBlank(message = "code is mandatory")
    public String code;

    @PositiveOrZero(message = "weight must be positive or zero")
    public Integer weight;
    
    @NotBlank(message = "fromLocation is mandatory")
    public String fromLocation;
    
    @NotBlank(message = "toLocation is mandatory")
    public String toLocation;
}
