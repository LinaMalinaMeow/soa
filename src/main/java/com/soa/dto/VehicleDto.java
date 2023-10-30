package com.soa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
    private String name;
    @NotNull
    @Valid
    private CoordinatesDto coordinates;
    @NotNull
    private Instant creationDate;
    @NotNull
    @Min(0)
    private BigDecimal enginePower;
    @NotNull
    private VehicleType type;
    private FuelType fuelType;
    private Integer numberOfWheels = 0;
}
