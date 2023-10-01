package com.soa.service;

import com.soa.service.db.VehicleDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class EnginePowerService {
    private VehicleDbService vehicleDbService;

    public BigDecimal getVehiclesSumEnginePower() {
        return vehicleDbService.findSumOfAllVehiclesEnginePower();
    }

    public BigDecimal getVehiclesAverageEnginePower() {
        return vehicleDbService.findAverageVehiclesEnginePower();
    }

}
