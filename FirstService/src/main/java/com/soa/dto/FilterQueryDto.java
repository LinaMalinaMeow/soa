package com.soa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@ToString
@AllArgsConstructor
@Accessors(chain = true)
public class FilterQueryDto {
    private Integer id;
    private String name;
    private BigDecimal minCoordinatesX;
    private BigDecimal maxCoordinatesX;
    private BigDecimal minCoordinatesY;
    private BigDecimal maxCoordinatesY;
    private String sort;
    private BigDecimal minEnginePower;
    private BigDecimal maxEnginePower;
    private Integer offset;
    private Integer limit;
    private VehicleType type;
    private FuelType fuelType;
}
