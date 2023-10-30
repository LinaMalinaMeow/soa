package com.soa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@Accessors(chain = true)
public class CoordinatesDto {
    @Max(6)
    private BigDecimal x;
    @NotNull
    @Max(197)
    private BigDecimal y;
}
