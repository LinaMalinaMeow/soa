package com.soa.controller;

import com.soa.dto.FilterQueryDto;
import com.soa.dto.VehicleDto;
import com.soa.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
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
        return ResponseEntity.status(200).body(vehicleService.updateVehicle(vehicleDto));
    }

    @PatchMapping("/{vehicle-id}/add-wheels/{number_of_wheels}")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable(name = "vehicle-id") Integer id, @PathVariable(name = "number_of_wheels") Integer numberOfWheels) {
        return ResponseEntity.status(200).body(vehicleService.addVehicleWheels(id, numberOfWheels));
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getVehicles(FilterQueryDto dto) {
        System.out.println(dto.toString());
        return ResponseEntity.status(200).body(vehicleService.getVehicles(dto));
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
