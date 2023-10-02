package com.soa.service;

import com.soa.converter.VehicleConverter;
import com.soa.dto.FilterQueryDto;
import com.soa.dto.VehicleDto;
import com.soa.dto.VehicleType;
import com.soa.dto.VehiclesListDTO;
import com.soa.entity.VehicleEntity;
import com.soa.exception.EntityNotFoundException;
import com.soa.filter.FilterService;
import com.soa.service.db.VehicleDbService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class VehicleService {
    private VehicleConverter vehicleConverter;
    private VehicleDbService vehicleDbService;
    private PageService pageService;

    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        vehicleDto.setId(null);
        return updateVehicle(vehicleDto);
    }

    public VehicleDto updateVehicle(VehicleDto vehicleDto) {
        VehicleEntity vehicle = vehicleConverter.convertToEntity(vehicleDto);
        vehicle = vehicleDbService.saveVehicle(vehicle);
        return vehicleConverter.convertToDto(vehicle);
    }

    public VehicleDto addVehicleWheels(Integer vehicleId, Integer numberOfWheels) {
        if (numberOfWheels <= 0) {
            throw new IllegalArgumentException("Количество добавляемых колес должно быть больше 0");
        }
        VehicleEntity vehicle = vehicleDbService.findById(vehicleId).orElseThrow(EntityNotFoundException::new);
        vehicle.setNumberOfWheels(vehicle.getNumberOfWheels() + numberOfWheels);
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


    public List<VehicleDto> getVehicles(FilterQueryDto dto) {
        FilterService.isValidRequestParams(dto);
        PageRequest request = pageService.getPageRequest(dto.getLimit(), dto.getOffset(), dto.getSort());
        return vehicleDbService.getVehicles(dto, request).stream().map(v -> vehicleConverter.convertToDto(v)).toList();
    }
    public VehiclesListDTO getVehicles2(FilterQueryDto dto) {
        FilterService.isValidRequestParams(dto);
        PageRequest request = pageService.getPageRequest(dto.getLimit(), dto.getOffset(), dto.getSort());
        return new VehiclesListDTO(vehicleDbService.getVehicles(dto, request).stream().map(v -> vehicleConverter.convertToDto(v)).toList());
    }
}
