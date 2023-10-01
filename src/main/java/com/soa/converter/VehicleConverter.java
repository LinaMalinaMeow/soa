package com.soa.converter;

import com.soa.dto.VehicleDto;
import com.soa.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VehicleConverter {
    private CoordinatesConverter coordinatesConverter;

    public VehicleEntity convertToEntity(VehicleDto dto) {
        VehicleEntity entity = new VehicleEntity();
        entity
                .setId(dto.getId())
                .setName(dto.getName())
                .setCoordinates(coordinatesConverter.convertToEntity(dto.getCoordinates()))
                .setCreationDate(dto.getCreationDate())
                .setEnginePower(dto.getEnginePower())
                .setType(dto.getType())
                .setFuelType(dto.getFuelType());
        return entity;
    }

    public VehicleDto convertToDto(VehicleEntity entity) {
        VehicleDto dto = new VehicleDto();
        dto
                .setId(entity.getId())
                .setName(entity.getName())
                .setCoordinates(coordinatesConverter.convertToDto(entity.getCoordinates()))
                .setCreationDate(entity.getCreationDate())
                .setEnginePower(entity.getEnginePower())
                .setType(entity.getType())
                .setFuelType(entity.getFuelType());
        return dto;
    }
}

