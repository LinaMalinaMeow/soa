package com.soa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@Accessors(chain = true)
public class CoordinatesEntity {
    private BigDecimal x;
    private BigDecimal y;
}
