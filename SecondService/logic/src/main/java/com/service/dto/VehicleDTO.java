package com.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class VehicleDTO implements Serializable {
    private Integer id;
    private String name;
    private CoordinatesDTO coordinates;
    private BigDecimal enginePower;
    private VehicleType type;
    private FuelType fuelType;
    private BigDecimal numberOfWheels;
}

enum FuelType {
    ELECTRICITY,
    DIESEL,
    NUCLEAR
}

enum VehicleType {
    HELICOPTER,
    DRONE,
    SHIP,
    MOTORCYCLE,
    HOVERBOARD
}
