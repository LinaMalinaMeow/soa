package com.soa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
@Setter
@Getter
@Accessors(chain = true)
public class CoordinatesDto {
    private BigDecimal x;
    private BigDecimal y;
}
