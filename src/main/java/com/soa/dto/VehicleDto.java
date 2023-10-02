package com.soa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@Accessors(chain = true)
public class VehicleDto {
    private Integer id;
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotNull
    private CoordinatesDto coordinates;
    @NotNull
    private Instant creationDate;
    @NotNull
    private BigDecimal enginePower;
    @NotNull
    private VehicleType type;
    @NotNull
    private FuelType fuelType;
    private Integer numberOfWheels = 0;
}
