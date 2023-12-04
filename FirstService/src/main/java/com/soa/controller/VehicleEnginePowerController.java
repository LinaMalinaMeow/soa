package com.soa.controller;

import com.soa.service.EnginePowerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@CrossOrigin
@RequestMapping("/vehicles/engine-power")
@AllArgsConstructor
public class VehicleEnginePowerController {
    private EnginePowerService enginePowerService;

    @GetMapping("/summ")
    public ResponseEntity<BigDecimal> getVehiclesSumEnginePower() {
        return ResponseEntity.status(200).body(enginePowerService.getVehiclesSumEnginePower());
    }

    @GetMapping("/average")
    public ResponseEntity<BigDecimal> getVehiclesAverageEnginePower() {
        return ResponseEntity.status(200).body(enginePowerService.getVehiclesAverageEnginePower());
    }
}
