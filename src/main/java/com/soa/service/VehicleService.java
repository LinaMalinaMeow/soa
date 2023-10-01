package com.soa.service;

import com.soa.converter.VehicleConverter;
import com.soa.dto.VehicleDto;
import com.soa.dto.VehicleType;
import com.soa.entity.VehicleEntity;
import com.soa.exception.EntityNotFoundException;
import com.soa.service.db.VehicleDbService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@AllArgsConstructor
public class VehicleService {
    private VehicleConverter vehicleConverter;
    private VehicleDbService vehicleDbService;

    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        vehicleDto.setId(null);
        return updateVehicle(vehicleDto);
    }

    public VehicleDto updateVehicle(VehicleDto vehicleDto) {
        VehicleEntity vehicle = vehicleConverter.convertToEntity(vehicleDto);
        vehicle = vehicleDbService.saveVehicle(vehicle);
        return vehicleConverter.convertToDto(vehicle);
    }

    public VehicleDto getVehicle(Integer vehicleId) {
        VehicleEntity vehicle = vehicleDbService.findById(vehicleId).orElseThrow(EntityNotFoundException::new);
        return vehicleConverter.convertToDto(vehicle);
    }

    public VehicleDto deleteVehicle(Integer vehicleId) {
        VehicleEntity vehicle = vehicleDbService.findById(vehicleId).orElseThrow(EntityNotFoundException::new);
        vehicleDbService.delete(vehicle);
        return vehicleConverter.convertToDto(vehicle);
    }

    public List<VehicleDto> deleteVehiclesByType(String type) {
        VehicleType vehicleType = VehicleType.valueOf(type.toUpperCase(Locale.ROOT));
        List<VehicleEntity> vehicles = vehicleDbService.findAllByType(vehicleType);
        if (vehicles.isEmpty()) {
            throw new EntityNotFoundException();
        }
        vehicleDbService.removeAllByType(vehicleType);
        return vehicles.stream().map(entity -> vehicleConverter.convertToDto(entity)).toList();
    }


    public List<VehicleDto> getVehicles(Map<String, String> requestParams) {
        return vehicleDbService.getVehicles(requestParams).stream().map(v -> vehicleConverter.convertToDto(v)).toList();
    }
}
