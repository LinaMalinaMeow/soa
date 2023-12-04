package com.soa.entity;

import com.soa.dto.FuelType;
import com.soa.dto.VehicleType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "Vehicle")
@Accessors(chain = true)
public class VehicleEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Embedded
    @JoinColumn(name = "COORDINATES_ID")
    private CoordinatesEntity coordinates;
    @Column(name = "CREATION_DATE")
    private Instant creationDate;
    @Column(name = "ENGINE_POWER")
    private BigDecimal enginePower;
    @Column(name = "TYPE")
    private VehicleType type;
    @Column(name = "FUEL_TYPE")
    private FuelType fuelType;
    @Column(name = "NUMBER_OF_WHEELS")
    private Integer numberOfWheels = 0;
}
