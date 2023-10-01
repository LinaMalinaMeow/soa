package com.soa.controller;

import com.soa.dto.VehicleDto;
import com.soa.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehicles")
@AllArgsConstructor
public class VehicleController {
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody @Valid VehicleDto vehicleDto) {
        return ResponseEntity.status(201).body(vehicleService.createVehicle(vehicleDto));
    }

    @PutMapping
    public ResponseEntity<VehicleDto> updateVehicle(@RequestBody @Valid VehicleDto vehicleDto) {
        return ResponseEntity.status(201).body(vehicleService.updateVehicle(vehicleDto));
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getVehicles(@RequestParam Map<String, String> requestParams) {
        return ResponseEntity.status(200).body(vehicleService.getVehicles(requestParams));
    }

    @GetMapping("/{vehicle-id}")
    public ResponseEntity<VehicleDto> getVehicles(@PathVariable(name = "vehicle-id") Integer id) {
        return ResponseEntity.status(200).body(vehicleService.getVehicle(id));
    }

    @DeleteMapping("/{vehicle-id}")
    public ResponseEntity<VehicleDto> deleteVehicle(@PathVariable(name = "vehicle-id") Integer id) {
        return ResponseEntity.status(200).body(vehicleService.deleteVehicle(id));
    }

    @DeleteMapping("/type/{type}")
    public ResponseEntity<List<VehicleDto>> deleteVehiclesByType(@PathVariable(name = "type") String type) {
        return ResponseEntity.status(200).body(vehicleService.deleteVehiclesByType(type));
    }
}
